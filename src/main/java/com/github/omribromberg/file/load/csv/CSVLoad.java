package com.github.omribromberg.file.load.csv;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.load.Loadable;
import com.github.omribromberg.file.load.FileLoad;

import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVLoad implements Loadable {
  private static final String DELIMETER = ",";

  private final FileLoad fileLoad;
  private String delimeter;

  public CSVLoad(String path) {
    this.fileLoad = new FileLoad(path);
    this.delimeter = DELIMETER;
  }

  private static Function<Event, String> mapper(String delimeter) {
    return event -> {
      return event.getAsMap().keySet().stream()
          .sorted()
          .map(event::get)
          .map(Object::toString)
          .collect(Collectors.joining(delimeter));
    };
  }

  public CSVLoad batchSize(int batchSize) {
    this.fileLoad.batchSize(batchSize);
    return this;
  }

  public CSVLoad delimeter(String delimeter) {
    this.delimeter = delimeter;
    return this;
  }

  @Override
  public void initialize() throws IOException {
    this.fileLoad.initialize();
  }

  @Override
  public void load(Stream<Event> events) {
    this.fileLoad.mapper(mapper(this.delimeter)).load(events);
  }
}
