package com.github.omribromberg.etl.pipeline.core.transform;

import com.github.omribromberg.etl.pipeline.core.common.Initializable;
import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.stream.Stream;

@FunctionalInterface
public interface Transformable extends Initializable {
  Stream<Event> transform(Stream<Event> events);
}
