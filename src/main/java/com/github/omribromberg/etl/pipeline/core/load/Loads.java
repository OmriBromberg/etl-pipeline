package com.github.omribromberg.etl.pipeline.core.load;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;

public class Loads {
  private Loads() {}

  public static CollectionLoad to(Collection<Event> referencedCollection) {
    return new CollectionLoad(referencedCollection);
  }
}
