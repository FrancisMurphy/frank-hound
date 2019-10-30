package com.hbfintech.hound.core.support;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.context.TraceContext;

public class TraceContextThreadLocalKeeper
{
    public static TransmittableThreadLocal<TraceContext> TRACE_TRACELOCAL_CONTEXT = new TransmittableThreadLocal<>();
}
