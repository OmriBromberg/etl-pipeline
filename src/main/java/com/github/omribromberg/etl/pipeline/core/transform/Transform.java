package com.github.omribromberg.etl.pipeline.core.transform;

import com.github.omribromberg.etl.pipeline.core.common.Initializable;
import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.function.Function;
import java.util.stream.Stream;

public interface Transform extends Function<Stream<Event>, Stream<Event>>, Initializable {}
