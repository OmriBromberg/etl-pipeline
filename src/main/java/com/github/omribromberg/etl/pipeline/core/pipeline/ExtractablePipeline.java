package com.github.omribromberg.etl.pipeline.core.pipeline;

import com.github.omribromberg.etl.pipeline.core.extract.Extractable;

public interface ExtractablePipeline {
  LoadablePipeline extract(Extractable extractable);
}
