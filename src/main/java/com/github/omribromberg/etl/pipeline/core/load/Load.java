package com.github.omribromberg.etl.pipeline.core.load;

import com.github.omribromberg.etl.pipeline.core.common.Initializable;
import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.Collection;
import java.util.function.Consumer;

public interface Load extends Consumer<Collection<Event>>, Initializable {}
