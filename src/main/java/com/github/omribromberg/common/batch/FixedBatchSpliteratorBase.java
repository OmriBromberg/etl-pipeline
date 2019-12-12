package com.github.omribromberg.common.batch;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;

import static java.util.Spliterators.spliterator;

public abstract class FixedBatchSpliteratorBase<T> implements Spliterator<T> {
  private final int batchSize;
  private final int characteristics;
  private long estimateSize;

  public FixedBatchSpliteratorBase(int characteristics, int batchSize, long estimateSize) {
    this.characteristics = characteristics | SUBSIZED;
    this.batchSize = batchSize;
    this.estimateSize = estimateSize;
  }

  public FixedBatchSpliteratorBase(int characteristics, int batchSize) {
    this(characteristics, batchSize, Long.MAX_VALUE);
  }

  public FixedBatchSpliteratorBase(int characteristics) {
    this(characteristics, 128, Long.MAX_VALUE);
  }

  public FixedBatchSpliteratorBase() {
    this(IMMUTABLE | ORDERED | NONNULL);
  }

  @Override
  public Spliterator<T> trySplit() {
    final HoldingConsumer<T> holder = new HoldingConsumer<>();
    if (!tryAdvance(holder)) {
      return null;
    }

    final Object[] batch = new Object[batchSize];
    int index = 0;
    do {
      batch[index] = holder.value;
    } while (++index < batchSize && tryAdvance(holder));

    if (estimateSize != Long.MAX_VALUE) {
      estimateSize -= index;
    }

    return spliterator(batch, 0, index, characteristics() | SIZED);
  }

  @Override
  public Comparator<? super T> getComparator() {
    if (hasCharacteristics(SORTED)) {
      return null;
    }
    throw new IllegalStateException();
  }

  @Override
  public long estimateSize() {
    return this.estimateSize;
  }

  @Override
  public int characteristics() {
    return this.characteristics;
  }

  static final class HoldingConsumer<T> implements Consumer<T> {
    Object value;

    @Override
    public void accept(T value) {
      this.value = value;
    }
  }
}
