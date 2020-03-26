package com.frank.hound.plugin.logback;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.frank.hound.core.acceptor.sorter.BaseSorter;
import com.frank.hound.core.common.StringUtils;
import com.frank.hound.core.constant.TraceContextConstants;
import com.frank.hound.core.context.TraceContext;
import com.frank.hound.core.support.HoundSheep;
import org.slf4j.MDC;

/**
 * @author frank
 */
@HoundSheep("logback")
public class LogbackSorter extends BaseSorter
{
    public LogbackSorter()
    {
        super();
    }

    @Override
    protected void sorting(TransmittableThreadLocal<TraceContext> traceContextThreadLocal)
    {
        if(null != traceContextThreadLocal.get() && !StringUtils
                .isEmpty(traceContextThreadLocal.get().getContext(
                        TraceContextConstants.TRACE_CONTEXT_HEAD)))
        {
            MDC.put(TraceContextConstants.TRACE_CONTEXT_HEAD, traceContextThreadLocal.get().getContext(
                    TraceContextConstants.TRACE_CONTEXT_HEAD).getData());
        }
    }
}
