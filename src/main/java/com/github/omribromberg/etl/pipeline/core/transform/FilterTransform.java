package com.github.omribromberg.etl.pipeline.core.transform;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterTransform implements Transform {
  private final Predicate<Event> predicate;

  FilterTransform(Predicate<Event> predicate) {
    this.predicate = predicate;
  }

  @Override
  public Stream<Event> apply(Stream<Event> events) {
    return events.filter(this.predicate);
  }
}
