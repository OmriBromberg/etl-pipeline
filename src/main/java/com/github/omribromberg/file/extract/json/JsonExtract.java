package com.github.omribromberg.file.extract.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.omribromberg.etl.pipeline.core.common.exceptions.ThrowableFunction;
import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.extract.Extractable;
import com.github.omribromberg.file.extract.FileExtract;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.omribromberg.etl.pipeline.core.common.exceptions.Exceptions.uncheck;
import static com.github.omribromberg.etl.pipeline.core.event.Event.event;

public class JsonExtract implements Extractable {
  private static final TypeReference<Map<String, Object>> TYPE_REFERENCE =
      new TypeReference<Map<String, Object>>() {};

  private final FileExtract fileExtract;
  private final ObjectMapper objectMapper;

  public JsonExtract(String path) {
    this.fileExtract = new FileExtract(path);
    this.objectMapper = new ObjectMapper();
  }

  private static Function<String, Event> mapper(ObjectMapper objectMapper) {
    ThrowableFunction<String, Event> throwableMapper =
        value -> event(objectMapper.readValue(value, TYPE_REFERENCE));
    return uncheck(throwableMapper);
  }

  public JsonExtract batchSize(int batchSize) {
    this.fileExtract.batchSize(batchSize);
    return this;
  }

  @Override
  public Stream<Event> extract() {
    return this.fileExtract.mapper(mapper(this.objectMapper)).extract();
  }

  @Override
  public void initialize() throws FileNotFoundException {
    this.fileExtract.initialize();
  }
}
