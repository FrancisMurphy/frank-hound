package com.hbfintech.hound.plugin.spring.resttemplate;

import com.hbfintech.hound.core.requester.packer.Packer;
import com.hbfintech.hound.core.support.HoundComponentContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class HoundRestTemplateInterceptor implements
        ClientHttpRequestInterceptor
{
    private Packer packer;

    public HoundRestTemplateInterceptor()
    {
        packer = HoundComponentContext.getContext().getComponent("restTemplate",Packer.class);
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException
    {
        packer.pack(request,execution);
        return execution.execute(request, body);
    }
}
