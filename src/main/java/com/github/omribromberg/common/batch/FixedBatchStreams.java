package com.github.omribromberg.common.batch;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;

import static java.util.stream.StreamSupport.stream;

public class FixedBatchStreams {
  private FixedBatchStreams() {
    throw new UnsupportedOperationException();
  }

  public static <T> Stream<T> batchedStream(Spliterator<T> spliterator, int batchSize) {
    return stream(new FixedBatchSpliterator<>(spliterator, batchSize), false);
  }

  public static <T> Stream<T> batchedStream(Stream<T> stream, int batchSize) {
    return batchedStream(stream.spliterator(), batchSize);
  }

  public static <T> Stream<T> batchedStream(Iterable<T> iterable, int batchSize) {
    return batchedStream(iterable.spliterator(), batchSize);
  }

  public static <T> Stream<T> batchedStream(Iterator<T> iterator, int batchSize) {
    return batchedStream(() -> iterator, batchSize);
  }
}
