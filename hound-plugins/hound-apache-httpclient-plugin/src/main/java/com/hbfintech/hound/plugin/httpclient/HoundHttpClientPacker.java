package com.hbfintech.hound.plugin.httpclient;

import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.requester.packer.Packer;
import com.hbfintech.hound.core.support.HoundComponent;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Map;

@HoundComponent("httpClient")
public class HoundHttpClientPacker implements Packer
{
    @Override
    public void pack(Object... unpackParams)
    {
        if(!(unpackParams.length==2
            &&unpackParams[0] instanceof HttpRequest
            &&unpackParams[1] instanceof HttpContext))
        {
            return;
        }

        HttpRequest request = (HttpRequest) unpackParams[0];

        TraceContext traceContext = TraceContextThreadLocalKeeper.TRACE_TRACELOCAL_CONTEXT
                .get();

        if (null == traceContext)
        {
            return;
        }

        for (Map.Entry<String, String> contextEntry : traceContext
                .getContexts())
        {
            final String contextKey = contextEntry.getKey();
            final String contextValue = contextEntry.getValue();
            request.addHeader(contextKey,contextValue);
        }
    }
}
