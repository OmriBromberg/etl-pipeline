package com.github.omribromberg.etl.pipeline.core.transform;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.load.Loads;
import com.github.omribromberg.etl.pipeline.core.pipeline.LoadablePipeline;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.stream.Stream;

public class TransformableJoinByKey implements Transformable {
  private final LoadablePipeline inMemoryPipeline;
  private final String inMemoryPiplineKey;
  private final String pipelineKey;

  TransformableJoinByKey(
      LoadablePipeline inMemoryPipeline, String inMemoryPiplineKey, String pipelineKey) {
    this.inMemoryPipeline = inMemoryPipeline;
    this.inMemoryPiplineKey = inMemoryPiplineKey;
    this.pipelineKey = pipelineKey;
  }

  @Override
  public Stream<Event> transform(Stream<Event> events) throws Throwable {
    MultiValuedMap<Object, Event> cachedPipeline = new HashSetValuedHashMap<>();
    this.inMemoryPipeline.load(Loads.loadTo(cachedPipeline, this.inMemoryPiplineKey)).run();

    return events.flatMap(
        event ->
            cachedPipeline.get(event.get(this.pipelineKey)).stream()
                .map(
                    cachedEvent -> {
                      event.getAsMap().putAll(cachedEvent.getAsMap());
                      return event;
                    }));
  }
}
