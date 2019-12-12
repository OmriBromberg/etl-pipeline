package com.github.omribromberg.file.load;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.load.Loadable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.omribromberg.etl.pipeline.core.common.exceptions.Exceptions.uncheck;

public class FileLoad implements Loadable {
  private static final String DELIMITER = "\n";
  private static final int BATCH_SIZE = 128;
  private static final String FIELD_NAME = "message";

  private final String path;

  private String delimiter;
  private int batchSize;
  private String fieldName;
  private Function<Event, String> mapper;

  private BufferedWriter bufferedWriter;

  public FileLoad(String path) {
    this.path = path;

    this.delimiter = DELIMITER;
    this.batchSize = BATCH_SIZE;
    this.fieldName = FIELD_NAME;

    this.mapper = event -> event.get(this.fieldName).toString();
  }

  public FileLoad delimiter(String delimiter) {
    this.delimiter = delimiter;
    return this;
  }

  public FileLoad batchSize(int batchSize) {
    this.batchSize = batchSize;
    return this;
  }

  public FileLoad fieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  public FileLoad mapper(Function<Event, String> mapper) {
    this.mapper = mapper;
    return this;
  }

  @Override
  public void initialize() throws IOException {
    this.bufferedWriter = Files.newBufferedWriter(Paths.get(this.path));
  }

  @Override
  public void load(Stream<Event> eventStream) {
    eventStream = eventStream.onClose(uncheck(() -> this.bufferedWriter.close()));
    AtomicInteger counter = new AtomicInteger();

    eventStream
        .map(this.mapper)
        .forEach(
            uncheck(
                raw -> {
                  this.bufferedWriter.write(raw);
                  this.bufferedWriter.write(delimiter);

                  if (counter.addAndGet(1) >= this.batchSize) {
                    this.bufferedWriter.flush();
                    counter.set(0);
                  }
                }));
  }
}
