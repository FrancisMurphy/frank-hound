package com.frank.hound.core.listener;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.frank.hound.core.context.TraceContext;
import com.frank.hound.core.event.BaseHoundEvent;
import com.frank.hound.core.event.ResetTraceContextEvent;
import com.frank.hound.core.support.TraceContextThreadLocalKeeper;

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
