package com.hbfintech.hound.plugin.feign;


import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Map;

/**
 * @author frank
 */
public class HoundFeignClientPacker implements RequestInterceptor
{
    @Override
    public void apply(RequestTemplate requestTemplate)
    {
        //获取现场上下文中的traceId
        TraceContext traceContext = TraceContextThreadLocalKeeper.TRACE_TRACELOCAL_CONTEXT.get();

        if(null == traceContext)
        {
            return;
        }

        for(Map.Entry<String,String> contextEntry:traceContext.getContexts())
        {
            final String contextKey = contextEntry.getKey();
            final String contextValue = contextEntry.getValue();
            requestTemplate.header(contextKey, contextValue);
        }
    }
}
