package com.hbfintech.hound.plugin.spring.mvc;

import com.hbfintech.hound.core.acceptor.unpacker.Unpacker;
import com.hbfintech.hound.core.support.HoundAutowired;

import javax.servlet.*;
import java.io.IOException;

public class HoundWebMvcFilter implements Filter
{
    @HoundAutowired("mvc")
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
