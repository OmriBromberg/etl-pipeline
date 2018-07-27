package com.github.omribromberg.etl.pipeline.core.pipeline;

import com.github.omribromberg.etl.pipeline.core.load.Loadable;
import com.github.omribromberg.etl.pipeline.core.transform.Transformable;

public interface LoadablePipeline {
  LoadablePipeline transform(Transformable transformable);

  RunnablePipeline load(Loadable loadable);
}
