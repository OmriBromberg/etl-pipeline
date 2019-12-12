package com.github.omribromberg.etl.pipeline.core.common.exceptions;

@FunctionalInterface
public interface ThrowableRunnable {
  void run() throws Exception;
}
