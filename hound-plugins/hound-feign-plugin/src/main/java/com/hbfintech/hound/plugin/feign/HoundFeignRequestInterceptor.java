package com.hbfintech.hound.plugin.feign;

import com.hbfintech.hound.core.requester.packer.Packer;
import com.hbfintech.hound.core.support.HoundContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class HoundFeignRequestInterceptor implements RequestInterceptor
{
    private Packer packer;

    public HoundFeignRequestInterceptor()
    {
        this.packer = HoundContext
                .getContext().getComponent("feign", Packer.class);
    }

    @Override
    public void apply(RequestTemplate requestTemplate)
    {
        packer.pack(requestTemplate);
    }
}
