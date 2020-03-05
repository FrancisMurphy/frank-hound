package com.frank.hound.core.support;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.frank.hound.core.context.TraceContext;

public class TraceContextThreadLocalKeeper
{
    public static final TransmittableThreadLocal<TraceContext> TRACE_LOCAL_CONTEXT = new TransmittableThreadLocal<>();
}
