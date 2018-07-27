package com.github.omribromberg.etl.pipeline.core.extract;

import com.github.omribromberg.etl.pipeline.core.common.Initializable;
import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.stream.Stream;

@FunctionalInterface
public interface Extractable extends Initializable {
  Stream<Event> extract();
}
