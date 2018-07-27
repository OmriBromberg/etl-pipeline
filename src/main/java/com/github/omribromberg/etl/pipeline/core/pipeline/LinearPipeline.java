package com.github.omribromberg.etl.pipeline.core.pipeline;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.extract.Extract;
import com.github.omribromberg.etl.pipeline.core.load.Load;
import com.github.omribromberg.etl.pipeline.core.transform.Transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class LinearPipeline implements ExtractablePipeline, LoadablePipeline, RunnablePipeline {
  private Extract extract;
  private Collection<Transform> transforms;
  private Load load;

  private LinearPipeline() {
    this.transforms = new ArrayList<>();
  }

  public static ExtractablePipeline linearPipeline() {
    return new LinearPipeline();
  }

  @Override
  public void run() {
    this.extract.initialize();
    this.transforms.forEach(Transform::initialize);
    this.load.initialize();

    Stream<Event> eventStream = this.extract.get();
    for (Transform transform : this.transforms) {
      eventStream = transform.apply(eventStream);
    }
    this.load.accept(eventStream);
  }

  @Override
  public LoadablePipeline extract(Extract extract) {
    this.extract = extract;
    return this;
  }

  @Override
  public LoadablePipeline transform(Transform transform) {
    this.transforms.add(transform);
    return this;
  }

  @Override
  public RunnablePipeline load(Load load) {
    this.load = load;
    return this;
  }
}
