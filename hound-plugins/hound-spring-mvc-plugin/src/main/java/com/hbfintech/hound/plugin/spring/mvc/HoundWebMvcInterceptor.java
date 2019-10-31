package com.hbfintech.hound.plugin.spring.mvc;

import com.hbfintech.hound.core.acceptor.unpacker.Unpacker;
import com.hbfintech.hound.core.support.HoundContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author frank
 */
public class HoundWebMvcInterceptor implements HandlerInterceptor
{

    private Unpacker mvcUnpacker;

    public HoundWebMvcInterceptor()
    {
        mvcUnpacker = HoundContext
                .getContext().getComponent("mvc", Unpacker.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler)
    {
        mvcUnpacker.unpack(request);
        return true;
    }
}
