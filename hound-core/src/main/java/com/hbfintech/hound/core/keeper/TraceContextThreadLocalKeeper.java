package com.hbfintech.hound.core.keeper;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.entity.HoundTraceContext;

public class TraceContextThreadLocalKeeper
{
    public static TransmittableThreadLocal<HoundTraceContext> TRACE_TRACELOCAL_CONTEXT = new TransmittableThreadLocal<>();
}
