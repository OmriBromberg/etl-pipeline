package com.github.omribromberg.etl.pipeline.core.pipeline;

import com.github.omribromberg.etl.pipeline.core.extract.Extract;
import com.github.omribromberg.etl.pipeline.core.load.Load;
import com.github.omribromberg.etl.pipeline.core.transform.Transform;

public interface Pipeline {
  void run();

  Pipeline extract(Extract extract);

  Pipeline transform(Transform transform);

  Pipeline load(Load load);
}
