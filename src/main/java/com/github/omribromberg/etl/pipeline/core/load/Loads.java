package com.github.omribromberg.etl.pipeline.core.load;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;

public class Loads {
  private Loads() {
    throw new UnsupportedOperationException();
  }

  public static CollectionLoad loadTo(Collection<Event> referencedCollection) {
    return new CollectionLoad(referencedCollection);
  }
}
