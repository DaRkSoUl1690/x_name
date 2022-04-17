package com.vedant.x_name.camera;

import com.daasuu.gpuv.egl.filter.GlFilter;

public interface FilterAdjuster {
    public void adjust(GlFilter filter, int percentage);
}
