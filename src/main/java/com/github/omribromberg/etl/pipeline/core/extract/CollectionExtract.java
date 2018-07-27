package com.github.omribromberg.etl.pipeline.core.extract;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;
import java.util.stream.Stream;

class CollectionExtract implements Extract {
  private final Collection<Event> events;

  CollectionExtract(Collection<Event> events) {
    this.events = events;
  }

  @Override
  public Stream<Event> get() {
    return this.events.stream();
  }
}
