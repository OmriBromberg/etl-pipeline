package com.github.omribromberg.etl.pipeline.core.transform;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.function.Function;
import java.util.stream.Stream;

public class MapTransform implements Transformable {
  private final Function<Event, Event> function;

  MapTransform(Function<Event, Event> function) {
    this.function = function;
  }

  @Override
  public Stream<Event> transform(Stream<Event> events) {
    return events.map(this.function);
  }
}
