package com.hbfintech.hound.plugin.spring.mvc;

import com.hbfintech.hound.core.acceptor.unpacker.Unpacker;
import com.hbfintech.hound.core.support.HoundAutowired;
import com.hbfintech.hound.core.support.HoundBridge;

import javax.servlet.*;
import java.io.IOException;

@HoundBridge(HoundWebMvcConstants.HOUND_WEB_MVC_NAME)
public class HoundWebMvcFilter implements Filter
{
    @HoundAutowired(HoundWebMvcConstants.HOUND_WEB_MVC_NAME)
    private Unpacker mvcUnpacker;

    public HoundWebMvcFilter()
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        mvcUnpacker.unpack(request);
        chain.doFilter(request, response);
    }
}
