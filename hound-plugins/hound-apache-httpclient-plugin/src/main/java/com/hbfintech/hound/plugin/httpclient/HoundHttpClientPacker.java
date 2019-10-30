package com.hbfintech.hound.plugin.httpclient;

import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Map;

public class HoundHttpClientPacker implements HttpRequestInterceptor
{

    public HoundHttpClientPacker()
    {
    }

    @Override
    public void process(HttpRequest request, HttpContext context)
            throws HttpException, IOException
    {
        //interceptor http request by httpClient

        //获取现场上下文中的traceId
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
