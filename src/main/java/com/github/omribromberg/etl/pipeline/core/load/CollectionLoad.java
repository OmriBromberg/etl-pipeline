package com.github.omribromberg.etl.pipeline.core.load;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionLoad implements Loadable {
  private final Collection<Event> referencedCollection;

  CollectionLoad(Collection<Event> referencedCollection) {
    this.referencedCollection = referencedCollection;
  }

  @Override
  public void load(Stream<Event> events) {
    referencedCollection.addAll(events.collect(Collectors.toList()));
  }
}
