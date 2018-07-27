package com.github.omribromberg.etl.pipeline.core.transform;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class TransformableFilter implements Transformable {
  private final Predicate<Event> predicate;

  TransformableFilter(Predicate<Event> predicate) {
    this.predicate = predicate;
  }

  @Override
  public Stream<Event> transform(Stream<Event> events) {
    return events.filter(this.predicate);
  }
}
