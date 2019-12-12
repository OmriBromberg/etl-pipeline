package com.github.omribromberg.etl.pipeline.core.common.exceptions;

@FunctionalInterface
public interface ThrowableFunction<T, R> {
  R apply(T element) throws Exception;
}
