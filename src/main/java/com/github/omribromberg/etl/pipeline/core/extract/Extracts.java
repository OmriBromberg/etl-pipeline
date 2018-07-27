package com.github.omribromberg.etl.pipeline.core.extract;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;

public class Extracts {
  private Extracts() {
    throw new UnsupportedOperationException();
  }

  public static CollectionExtract of(Collection<Event> events) {
    return new CollectionExtract(events);
  }
}
