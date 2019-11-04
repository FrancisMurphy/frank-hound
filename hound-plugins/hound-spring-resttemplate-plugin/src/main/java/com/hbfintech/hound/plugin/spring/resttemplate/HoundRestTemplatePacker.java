package com.hbfintech.hound.plugin.spring.resttemplate;

import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.requester.packer.Packer;
import com.hbfintech.hound.core.support.HoundComponent;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * @author frank
 */
@HoundComponent("restTemplate")
public class HoundRestTemplatePacker implements Packer
{
    private static final int RESTTEMPLATE_PARAM_LENGTH = 2;

    @Override
    public void pack(Object... unpackParams)
    {
        if(!(unpackParams.length == RESTTEMPLATE_PARAM_LENGTH
                &&unpackParams[0] instanceof HttpRequest
                &&unpackParams[1] instanceof ClientHttpRequestExecution))
        {
            return;
        }

        HttpRequest request = (HttpRequest) unpackParams[0];

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
            request.getHeaders().add(contextKey, contextValue);
        }
    }
}
