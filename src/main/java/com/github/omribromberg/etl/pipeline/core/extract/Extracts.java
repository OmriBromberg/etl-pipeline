package com.github.omribromberg.etl.pipeline.core.extract;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;

public class Extracts {
  private Extracts() {
    throw new UnsupportedOperationException();
  }

  public static ExtractableCollection extractFrom(Collection<Event> events) {
    return new ExtractableCollection(events);
  }
}
