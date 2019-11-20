package com.hbfintech.hound.plugin.logback;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.acceptor.sorter.BaseSorter;
import com.hbfintech.hound.core.constant.TraceContextConstants;
import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.support.HoundSheep;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

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
                    TraceContextConstants.TRACE_CONTEXT_HEAD));
        }
    }
}
