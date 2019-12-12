package com.github.omribromberg.etl.pipeline.core.common.exceptions;

import java.util.function.Consumer;
import java.util.function.Function;

public class Exceptions {
  private Exceptions() {
    throw new UnsupportedOperationException();
  }

  public static <T> Consumer<T> uncheck(ThrowableConsumer<T> throwableConsumer) {
    return value -> {
      try {
        throwableConsumer.accept(value);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  public static <T, R> Function<T, R> uncheck(ThrowableFunction<T, R> throwableFunction) {
    return value -> {
      try {
        return throwableFunction.apply(value);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  public static Runnable uncheck(ThrowableRunnable throwableRunnable) {
    return () -> {
      try {
        throwableRunnable.run();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }
}
