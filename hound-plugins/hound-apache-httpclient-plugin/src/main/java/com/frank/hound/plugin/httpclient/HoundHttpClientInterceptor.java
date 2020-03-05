package com.frank.hound.plugin.httpclient;

import com.frank.hound.core.requester.packer.Packer;
import com.frank.hound.core.support.HoundAutowired;
import com.frank.hound.core.support.HoundBridge;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

@HoundBridge("httpClient")
public class HoundHttpClientInterceptor implements HttpRequestInterceptor
{

    @HoundAutowired("httpClient")
    private Packer httpClientPacker;

    @Override
    public void process(HttpRequest request, HttpContext context)
            throws HttpException, IOException
    {
        httpClientPacker.pack(request::addHeader);
    }
}
