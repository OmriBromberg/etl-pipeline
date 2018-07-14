package com.github.omribromberg.etl.pipeline.core.transform;

import com.github.omribromberg.etl.pipeline.core.common.Initializable;
import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;
import java.util.function.Function;

public interface Transform
    extends Function<Collection<Event>, Collection<Event>>, Initializable {}
