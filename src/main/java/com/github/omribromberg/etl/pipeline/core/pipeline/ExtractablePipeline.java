package com.github.omribromberg.etl.pipeline.core.pipeline;

import com.github.omribromberg.etl.pipeline.core.extract.Extract;

public interface ExtractablePipeline {
  LoadablePipeline extract(Extract extract);
}
