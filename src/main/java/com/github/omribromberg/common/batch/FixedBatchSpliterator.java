package com.github.omribromberg.common.batch;

import java.util.Spliterator;
import java.util.function.Consumer;

public class FixedBatchSpliterator<T> extends FixedBatchSpliteratorBase<T> {
  private final Spliterator<T> spliterator;

  public FixedBatchSpliterator(Spliterator<T> toWrap, int batchSize) {
    super(toWrap.characteristics(), batchSize, toWrap.estimateSize());
    this.spliterator = toWrap;
  }

  @Override
  public boolean tryAdvance(Consumer<? super T> action) {
    return this.spliterator.tryAdvance(action);
  }

  @Override
  public void forEachRemaining(Consumer<? super T> action) {
    this.spliterator.forEachRemaining(action);
  }
}
