package com.github.omribromberg.file.extract.csv;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.extract.Extractable;
import com.github.omribromberg.file.extract.FileExtract;

import java.io.FileNotFoundException;
import java.util.function.Function;
import java.util.stream.Stream;

public class CSVExtract implements Extractable {
  private static final String DELIMETER = ",";

  private final FileExtract fileExtract;
  private String delimeter;

  public CSVExtract(String path) {
    this.fileExtract = new FileExtract(path);
    this.delimeter = DELIMETER;
  }

  private static Function<String, Event> mapper(String delimeter) {
    return value -> {
      String[] parsed = value.split(delimeter);
      Event event = new Event();

      for (int i = 0; i < parsed.length; i++) {
        event.put(String.valueOf(i), parsed[i]);
      }

      return event;
    };
  }

  public CSVExtract batchSize(int batchSize) {
    this.fileExtract.batchSize(batchSize);
    return this;
  }

  public CSVExtract delimeter(String delimeter) {
    this.delimeter = delimeter;
    return this;
  }

  @Override
  public Stream<Event> extract() {
    return this.fileExtract.mapper(mapper(this.delimeter)).extract();
  }

  @Override
  public void initialize() throws FileNotFoundException {
    this.fileExtract.initialize();
  }
}
