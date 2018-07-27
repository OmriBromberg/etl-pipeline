package com.github.omribromberg.etl.pipeline.core.pipeline;

import com.github.omribromberg.etl.pipeline.core.load.Load;
import com.github.omribromberg.etl.pipeline.core.transform.Transform;

public interface LoadablePipeline {
  LoadablePipeline transform(Transform transform);

  RunnablePipeline load(Load load);
}
