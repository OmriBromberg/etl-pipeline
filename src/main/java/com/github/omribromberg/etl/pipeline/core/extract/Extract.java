package com.github.omribromberg.etl.pipeline.core.extract;

import com.github.omribromberg.etl.pipeline.core.common.Initializable;
import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Extract extends Supplier<Stream<Event>>, Initializable {}
