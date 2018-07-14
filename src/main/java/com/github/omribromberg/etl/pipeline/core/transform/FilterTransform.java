package com.github.omribromberg.etl.pipeline.core.transform;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterTransform implements Transform {
  private final Predicate<Event> predicate;

  FilterTransform(Predicate<Event> predicate) {
    this.predicate = predicate;
  }

  @Override
  public Collection<Event> apply(Collection<Event> events) {
    return events.stream().filter(this.predicate).collect(Collectors.toList());
  }
}
