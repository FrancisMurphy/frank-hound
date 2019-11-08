package com.hbfintech.hound.plugin.feign;


import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.requester.packer.BaseKvPacker;
import com.hbfintech.hound.core.requester.packer.Packer;
import com.hbfintech.hound.core.support.HoundComponent;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;
import feign.RequestTemplate;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author frank
 */
@HoundComponent("feign")
public class FeignPacker extends BaseKvPacker
{
    public FeignPacker()
    {
    }
}
