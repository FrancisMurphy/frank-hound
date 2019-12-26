package com.hbfintech.hound.core.support;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.context.TraceContext;

public class TraceContextThreadLocalKeeper
{
    public static final TransmittableThreadLocal<TraceContext> TRACE_LOCAL_CONTEXT = new TransmittableThreadLocal<>();
}
