package com.github.omribromberg.etl.pipeline.core.load;

import com.github.omribromberg.etl.pipeline.core.common.Initializable;
import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.stream.Stream;

@FunctionalInterface
public interface Loadable extends Initializable {
  void load(Stream<Event> events) throws Throwable;
}
