package com.github.omribromberg.etl.pipeline.core.event;

import java.util.HashMap;
import java.util.Map;

public class Event {
  private final Map<String, Object> innerMap;

  public Event() {
    this.innerMap = new HashMap<>();
  }

  public static Event event(Map<String, Object> map) {
    Event event = new Event();
    event.innerMap.putAll(map);
    return event;
  }

  public <T> T get(String name) {
    return (T) innerMap.get(name);
  }

  public Event put(String name, Object value) {
    this.innerMap.put(name, value);
    return this;
  }

  public boolean has(String name) {
    return this.innerMap.containsKey(name);
  }

  public Event remove(String name) {
    this.innerMap.remove(name);
    return this;
  }

  public <T> T pop(String name) {
    return (T) this.innerMap.remove(name);
  }

  public Event clear() {
    this.innerMap.clear();
    return this;
  }

  @Override
  public String toString() {
    return this.innerMap.toString();
  }
}
