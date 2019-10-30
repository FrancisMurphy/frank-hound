package com.hbfintech.hound.plugin.logback;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.acceptor.sorter.BaseSorter;
import com.hbfintech.hound.core.annotation.HoundComponent;
import com.hbfintech.hound.core.constant.TraceContextConstants;
import com.hbfintech.hound.core.context.TraceContext;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author frank
 */
@HoundComponent("logback")
public class LogbackSorter extends BaseSorter
{
    public LogbackSorter()
    {
        super();
    }

    @Override
    protected void sorting(TransmittableThreadLocal<TraceContext> traceContextThreadLocal)
    {
        if(null == traceContextThreadLocal.get() || StringUtils
                .isEmpty(traceContextThreadLocal.get().getContext(
                        TraceContextConstants.TRACE_CONTEXT_HEAD)))
        {
            String newTraceId = initTraceId(traceContextThreadLocal);

            MDC.put(TraceContextConstants.TRACE_CONTEXT_HEAD, newTraceId);
        }
        else
        {
            MDC.put(TraceContextConstants.TRACE_CONTEXT_HEAD, traceContextThreadLocal.get().getContext(
                    TraceContextConstants.TRACE_CONTEXT_HEAD));
        }
    }

    /**
     * Init traceId that can not find traceId in trace context...
     * @param traceContextThreadLocal
     * @return
     */
    private String initTraceId(
            TransmittableThreadLocal<TraceContext> traceContextThreadLocal)
    {
        UUID uuid = UUID.randomUUID();
        String newTraceId = uuid.toString().replace("-", "");

        TraceContext traceContext = new TraceContext();
        traceContext.addContext(TraceContextConstants.TRACE_CONTEXT_HEAD, newTraceId);
        traceContextThreadLocal.set(traceContext);
        return newTraceId;
    }
}
