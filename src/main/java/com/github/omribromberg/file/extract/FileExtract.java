package com.github.omribromberg.file.extract;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.extract.Extractable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.omribromberg.common.batch.FixedBatchStreams.batchedStream;
import static com.github.omribromberg.etl.pipeline.core.event.Event.event;

public class FileExtract implements Extractable {
  private static final String DELIMITER = "\n";
  private static final int BATCH_SIZE = 100;
  private static final String FIELD_NAME = "message";

  private final String path;

  private String delimiter;
  private int batchSize;
  private String fieldName;
  private Function<String, Event> mapper;

  private Scanner scanner;

  public FileExtract(String path) {
    this.path = path;

    this.delimiter = DELIMITER;
    this.batchSize = BATCH_SIZE;
    this.fieldName = FIELD_NAME;

    this.mapper = value -> event(this.fieldName, value);
  }

  public FileExtract delimiter(String delimiter) {
    this.delimiter = delimiter;
    return this;
  }

  public FileExtract batchSize(int batchSize) {
    this.batchSize = batchSize;
    return this;
  }

  public FileExtract fieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  public FileExtract mapper(Function<String, Event> mapper) {
    this.mapper = mapper;
    return this;
  }

  @Override
  public void initialize() throws FileNotFoundException {
    this.scanner = new Scanner(new File(this.path)).useDelimiter(this.delimiter);
  }

  @Override
  public Stream<Event> extract() {
    return batchedStream(scanner, this.batchSize)
        .onClose(() -> this.scanner.close())
        .map(this.mapper);
  }
}
