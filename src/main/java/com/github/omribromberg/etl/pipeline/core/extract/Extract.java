package com.github.omribromberg.etl.pipeline.core.extract;

import com.github.omribromberg.etl.pipeline.core.common.Initializable;
import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;

public interface Extract extends Iterable<Collection<Event>>, Initializable {}
