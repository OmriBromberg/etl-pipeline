package com.github.omribromberg.etl.pipeline.core.load;

import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoadableMap implements Loadable {
  private final Map<Object, Event> referencedMap;
  private final String keyField;

  LoadableMap(Map<Object, Event> referencedMap, String keyField) {
    this.referencedMap = referencedMap;
    this.keyField = keyField;
  }

  @Override
  public void load(Stream<Event> events) {
    referencedMap.putAll(
        events.collect(Collectors.toMap((event) -> event.get(this.keyField), Function.identity())));
  }
}
