package com.github.omribromberg.etl.pipeline.core.pipeline;

import com.github.omribromberg.etl.pipeline.core.event.Event;
import com.github.omribromberg.etl.pipeline.core.extract.Extract;
import com.github.omribromberg.etl.pipeline.core.load.Load;
import com.github.omribromberg.etl.pipeline.core.transform.Transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LinearPipeline implements Pipeline {
  private Extract extract;
  private Collection<Transform> transforms;
  private Load load;

  public LinearPipeline() {
    this.transforms = new ArrayList<>();
  }

  @Override
  public void run() {
    this.extract.initialize();
    this.transforms.forEach(Transform::initialize);
    this.load.initialize();

    Stream<Collection<Event>> eventStream = StreamSupport.stream(extract.spliterator(), false);
    for (Transform transform : this.transforms) {
      eventStream = eventStream.map(transform);
    }
    eventStream.forEach(this.load);
  }

  @Override
  public Pipeline extract(Extract extract) {
    this.extract = extract;
    return this;
  }

  @Override
  public Pipeline transform(Transform transform) {
    this.transforms.add(transform);
    return this;
  }

  @Override
  public Pipeline load(Load load) {
    this.load = load;
    return this;
  }
}
