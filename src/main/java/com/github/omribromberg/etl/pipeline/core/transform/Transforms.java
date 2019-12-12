package com.github.omribromberg.etl.pipeline.core.transform;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.pipeline.LoadablePipeline;

import java.util.function.Function;
import java.util.function.Predicate;

public class Transforms {
  private Transforms() {
    throw new UnsupportedOperationException();
  }

  public static TransformableFilter filter(Predicate<Event> predicate) {
    return new TransformableFilter(predicate);
  }

  public static TransformableMap map(Function<Event, Event> function) {
    return new TransformableMap(function);
  }

  public static TransformableJoinByKey joinByKey(
      LoadablePipeline inMemoryPipeline, String inMemoryPiplineKey, String pipelineKey) {
    return new TransformableJoinByKey(inMemoryPipeline, inMemoryPiplineKey, pipelineKey);
  }
}
