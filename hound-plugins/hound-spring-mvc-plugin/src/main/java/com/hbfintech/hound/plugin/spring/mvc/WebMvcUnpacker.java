package com.hbfintech.hound.plugin.spring.mvc;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.acceptor.unpacker.BasicUnpacker;
import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.support.HoundComponent;
import com.hbfintech.hound.core.support.TraceContextAssistant;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author frank
 */
@HoundComponent("mvc")
public class WebMvcUnpacker extends BasicUnpacker
{

    @Override
    protected void unpacking(Object... unpackParams)
    {

        if(!(1==unpackParams.length &&
                unpackParams[0] instanceof HttpServletRequest))
        {
            return;
        }

        HttpServletRequest request = (HttpServletRequest) unpackParams[0];

        try
        {
            TransmittableThreadLocal<TraceContext> traceContextThreadLocal = TraceContextThreadLocalKeeper.TRACE_TRACELOCAL_CONTEXT;

            //获取上下文参数
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null)
            {
                while (headerNames.hasMoreElements())
                {
                    final String headerName = headerNames.nextElement();
                    if (TraceContextAssistant.isTraceKeyContain(headerName))
                    {
                        final String headerValue = request
                                .getHeader(headerName);
                        //放入线程上下文
                        TraceContext traceContext = new TraceContext();
                        traceContext.addContext(headerName, headerValue);
                        traceContextThreadLocal.set(traceContext);
                    }
                }
            }
        }
        catch (Exception e)
        {
            //do nothing
        }
    }
}


