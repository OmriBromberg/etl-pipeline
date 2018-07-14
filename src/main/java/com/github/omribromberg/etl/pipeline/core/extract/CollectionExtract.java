package com.github.omribromberg.etl.pipeline.core.extract;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

class CollectionExtract implements Extract {
  private final Collection<Event> events;

  CollectionExtract(Collection<Event> events) {
    this.events = events;
  }

  @Override
  public Iterator<Collection<Event>> iterator() {
    return Collections.singletonList(events).iterator();
  }
}
