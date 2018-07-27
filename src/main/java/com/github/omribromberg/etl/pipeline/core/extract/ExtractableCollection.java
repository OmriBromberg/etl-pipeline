package com.github.omribromberg.etl.pipeline.core.extract;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;
import java.util.stream.Stream;

class ExtractableCollection implements Extractable {
  private final Collection<Event> events;

  ExtractableCollection(Collection<Event> events) {
    this.events = events;
  }

  @Override
  public Stream<Event> extract() {
    return this.events.stream();
  }
}
