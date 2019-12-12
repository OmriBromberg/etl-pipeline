package com.github.omribromberg.etl.pipeline.core.common.exceptions;

@FunctionalInterface
public interface ThrowableConsumer<T> {
  void accept(T t) throws Exception;
}
