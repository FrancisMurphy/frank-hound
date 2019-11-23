package com.hbfintech.hound.core.listener;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.constant.TraceContextConstants;
import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.event.HoundEvent;
import com.hbfintech.hound.core.event.UnpackedEvent;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author frank
 */
public class UnpackedTraceIdListener implements HoundEventListener
{
    private TransmittableThreadLocal<TraceContext> traceContextThreadLocal = TraceContextThreadLocalKeeper.TRACE_TRACELOCAL_CONTEXT;

    @Override
    public void onEvent(HoundEvent event)
    {
        if(event instanceof UnpackedEvent)
        {
            initTraceId(traceContextThreadLocal);
        }
    }

    /**
     * Init traceId that can not find traceId in trace context...
     * @param traceContextThreadLocal threadlocal
     * @return
     */
    private void initTraceId(
            TransmittableThreadLocal<TraceContext> traceContextThreadLocal)
    {
        if(null == traceContextThreadLocal.get() || StringUtils
                .isEmpty(traceContextThreadLocal.get().getContext(
                        TraceContextConstants.TRACE_CONTEXT_HEAD)))
        {
            UUID uuid = UUID.randomUUID();
            String newTraceId = uuid.toString().replace("-", "");

            TraceContext traceContext = new TraceContext();
            traceContext.addContext(TraceContextConstants.TRACE_CONTEXT_HEAD, newTraceId);
            traceContextThreadLocal.set(traceContext);
        }
    }
}
