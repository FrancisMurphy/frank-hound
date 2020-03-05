package com.frank.hound.plugin.spring.resttemplate;

import com.frank.hound.core.requester.packer.Packer;
import com.frank.hound.core.support.HoundAutowired;
import com.frank.hound.core.support.HoundBridge;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@HoundBridge("restTemplate")
public class HoundRestTemplateInterceptor implements
        ClientHttpRequestInterceptor
{
    @HoundAutowired("restTemplate")
    private Packer packer;

    public HoundRestTemplateInterceptor()
    {
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException
    {
        packer.pack((traceKey,traceValue)->request.getHeaders().add(traceKey,traceValue));
        return execution.execute(request, body);
    }
}
