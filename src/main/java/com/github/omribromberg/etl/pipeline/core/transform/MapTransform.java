package com.github.omribromberg.etl.pipeline.core.transform;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapTransform implements Transform {
  private final Function<Event, Event> function;

  MapTransform(Function<Event, Event> function) {
    this.function = function;
  }

  @Override
  public Collection<Event> apply(Collection<Event> events) {
    return events.stream().map(this.function).collect(Collectors.toList());
  }
}
