package com.github.omribromberg.file.load.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.load.Loadable;
import com.github.omribromberg.file.load.FileLoad;

import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.omribromberg.etl.pipeline.core.common.exceptions.Exceptions.uncheck;

public class JsonLoad implements Loadable {
  private final FileLoad fileLoad;
  private final ObjectMapper objectMapper;

  public JsonLoad(String path) {
    this.fileLoad = new FileLoad(path);
    this.objectMapper = new ObjectMapper();
  }

  private static Function<Event, String> mapper(ObjectMapper objectMapper) {
    return uncheck(
        event -> {
          return objectMapper.writeValueAsString(event.getAsMap());
        });
  }

  public JsonLoad batchSize(int batchSize) {
    this.fileLoad.batchSize(batchSize);
    return this;
  }

  @Override
  public void load(Stream<Event> events) {
    this.fileLoad.mapper(mapper(this.objectMapper)).load(events);
  }
}
