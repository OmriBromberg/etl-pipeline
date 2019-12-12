package com.github.omribromberg.etl.pipeline.core.load;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.stream.Stream;

public class LoadableMultiValueMap implements Loadable {
  private final MultiValuedMap<Object, Event> referencedMap;
  private final String keyField;

  LoadableMultiValueMap(MultiValuedMap<Object, Event> referencedMap, String keyField) {
    this.referencedMap = referencedMap;
    this.keyField = keyField;
  }

  @Override
  public void load(Stream<Event> events) {
    events.forEach(event -> this.referencedMap.put(event.get(this.keyField), event));
  }
}
