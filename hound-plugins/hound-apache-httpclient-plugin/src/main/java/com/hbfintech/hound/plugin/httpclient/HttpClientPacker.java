package com.hbfintech.hound.plugin.httpclient;

import com.hbfintech.hound.core.requester.packer.BaseKvPacker;
import com.hbfintech.hound.core.support.HoundSheep;

/**
 * 可能增加传输规则
 */
@HoundSheep("httpClient")
public class HttpClientPacker extends BaseKvPacker
{
    public HttpClientPacker()
    {
    }
}
