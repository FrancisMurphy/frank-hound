package com.hbfintech.hound.plugin.spring.mvc.interceptor;

import com.hbfintech.hound.core.acceptor.unpacker.Unpacker;
import com.hbfintech.hound.core.context.HoundCoreContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author frank
 */
public class MvcTraceInterceptor implements HandlerInterceptor
{

    private Unpacker mvcUnpacker;

    public MvcTraceInterceptor()
    {
        mvcUnpacker = HoundCoreContext
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
