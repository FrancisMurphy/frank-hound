package com.hbfintech.hound.plugin.feign;

import com.hbfintech.hound.core.requester.packer.Packer;
import com.hbfintech.hound.core.support.HoundAutowired;
import com.hbfintech.hound.core.support.HoundBridge;
import feign.RequestInterceptor;
import feign.RequestTemplate;

@HoundBridge("feign")
public class HoundFeignRequestInterceptor implements RequestInterceptor
{
    @HoundAutowired("feign")
    private Packer packer;

    public HoundFeignRequestInterceptor()
    {
    }

    @Override
    public void apply(RequestTemplate requestTemplate)
    {
        packer.pack(requestTemplate::header);
    }
}
