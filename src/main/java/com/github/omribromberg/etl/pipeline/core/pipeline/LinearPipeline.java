package com.github.omribromberg.etl.pipeline.core.pipeline;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.extract.Extractable;
import com.github.omribromberg.etl.pipeline.core.load.Loadable;
import com.github.omribromberg.etl.pipeline.core.transform.Transformable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class LinearPipeline implements ExtractablePipeline, LoadablePipeline, RunnablePipeline {
  private Extractable extractable;
  private Collection<Transformable> transformables;
  private Loadable loadable;

  private LinearPipeline() {
    this.transformables = new ArrayList<>();
  }

  public static ExtractablePipeline linearPipeline() {
    return new LinearPipeline();
  }

  @Override
  public void run() {
    this.extractable.initialize();
    this.transformables.forEach(Transformable::initialize);
    this.loadable.initialize();

    Stream<Event> eventStream = this.extractable.extract();
    for (Transformable transformable : this.transformables) {
      eventStream = transformable.transform(eventStream);
    }
    this.loadable.load(eventStream);
  }

  @Override
  public LoadablePipeline extract(Extractable extractable) {
    this.extractable = extractable;
    return this;
  }

  @Override
  public LoadablePipeline transform(Transformable transformable) {
    this.transformables.add(transformable);
    return this;
  }

  @Override
  public RunnablePipeline load(Loadable loadable) {
    this.loadable = loadable;
    return this;
  }
}
