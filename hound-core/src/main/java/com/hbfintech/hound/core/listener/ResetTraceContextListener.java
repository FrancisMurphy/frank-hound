package com.hbfintech.hound.core.listener;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.event.BaseHoundEvent;
import com.hbfintech.hound.core.event.ResetTraceContextEvent;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;

/**
 * @author frank
 */
public class ResetTraceContextListener implements HoundEventListener
{
    private TransmittableThreadLocal<TraceContext> traceContextThreadLocal = TraceContextThreadLocalKeeper.TRACE_LOCAL_CONTEXT;

    @Override
    public void onEvent(BaseHoundEvent event)
    {
        if(event instanceof ResetTraceContextEvent)
        {
            traceContextThreadLocal.set(null);
        }
    }
}
