package com.github.omribromberg.etl.pipeline.core.load;

import com.github.omribromberg.etl.pipeline.core.common.Initializable;
import com.github.omribromberg.etl.pipeline.core.event.Event;

import java.util.function.Consumer;
import java.util.stream.Stream;

public interface Load extends Consumer<Stream<Event>>, Initializable {}
