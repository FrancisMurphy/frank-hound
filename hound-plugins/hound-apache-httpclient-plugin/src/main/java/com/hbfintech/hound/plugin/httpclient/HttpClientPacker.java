package com.hbfintech.hound.plugin.httpclient;

import com.hbfintech.hound.core.requester.packer.BaseKvPacker;
import com.hbfintech.hound.core.support.HoundComponent;

/**
 * 可能增加传输规则
 */
@HoundComponent("httpClient")
public class HttpClientPacker extends BaseKvPacker
{
    public HttpClientPacker()
    {
    }
}
