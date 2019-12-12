package com.github.omribromberg.etl.pipeline.core.load;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.Collection;
import java.util.Map;

public class Loads {
  private Loads() {
    throw new UnsupportedOperationException();
  }

  public static LoadableCollection loadTo(Collection<Event> referencedCollection) {
    return new LoadableCollection(referencedCollection);
  }

  public static LoadableMap loadTo(Map<Object, Event> referencedMap, String keyField) {
    return new LoadableMap(referencedMap, keyField);
  }

  public static LoadableMultiValueMap loadTo(
      MultiValuedMap<Object, Event> referencedMap, String keyField) {
    return new LoadableMultiValueMap(referencedMap, keyField);
  }
}
