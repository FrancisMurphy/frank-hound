package com.hbfintech.hound.plugin.spring.mvc;

import com.hbfintech.hound.core.acceptor.unpacker.Unpacker;
import com.hbfintech.hound.core.support.HoundComponentContext;

import javax.servlet.*;
import java.io.IOException;

public class HoundWebMvcFilter implements Filter
{
    private Unpacker mvcUnpacker;

    public HoundWebMvcFilter()
    {
        mvcUnpacker = HoundComponentContext
                .getContext().getComponent("mvc", Unpacker.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        mvcUnpacker.unpack(request);
        chain.doFilter(request, response);
    }
}
