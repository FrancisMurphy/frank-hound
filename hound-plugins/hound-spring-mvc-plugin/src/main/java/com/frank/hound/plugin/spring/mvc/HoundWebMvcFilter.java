package com.frank.hound.plugin.spring.mvc;

import com.frank.hound.core.acceptor.unpacker.Unpacker;
import com.frank.hound.core.codec.protocol.ParsedContent;
import com.frank.hound.core.codec.protocol.http.HttpCompositionEnum;
import com.frank.hound.core.constant.ContextElementType;
import com.frank.hound.core.context.ContextElement;
import com.frank.hound.core.event.ResetTraceContextEvent;
import com.frank.hound.core.support.HoundAutowired;
import com.frank.hound.core.support.HoundBridge;
import com.frank.hound.core.support.Sheepehound;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@HoundBridge(HoundWebMvcConstants.HOUND_WEB_MVC_NAME)
public class HoundWebMvcFilter implements Filter
{
    @HoundAutowired(HoundWebMvcConstants.HOUND_WEB_MVC_NAME)
    private Unpacker mvcUnpacker;

    public HoundWebMvcFilter()
    {
    }

    /**
     * This method needs to be implemented for downward compatibility
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        //do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        try
        {
            if (!(request instanceof HttpServletRequest))
            {
                return;
            }

            HttpServletRequest httpRequest = (HttpServletRequest) request;

            //获取头部参数
            ParsedContent parsedContent = new ParsedContent();
            Enumeration<String> headerNames = httpRequest.getHeaderNames();
            if (headerNames != null)
            {
                while (headerNames.hasMoreElements())
                {
                    final String headerName = headerNames.nextElement();
                    parsedContent.addElement(headerName,new ContextElement<String>(ContextElementType.CORE_CONTEXT,httpRequest
                        .getHeader(headerName),String.class));
                }
            }

            //获取body中的元素
//            Enumeration<String> bodyElement = httpRequest.getHeaderNames();
//            if (headerNames != null)
//            {
//                while (headerNames.hasMoreElements())
//                {
//                    final String headerName = headerNames.nextElement();
//                    targetHeaders.put(headerName, httpRequest
//                        .getHeader(headerName));
//                }
//            }
//
//            HttpProtocolDescribe httpProtocolDescribe = new HttpProtocolDescribe();
//            httpProtocolDescribe



//            mvcUnpacker.unpack(targetHeaders);
            chain.doFilter(request, response);
        }
        finally
        {
            try
            {
                //Clean threadlocal event
                Sheepehound.getHound().publishEvent(new ResetTraceContextEvent(Sheepehound.getHound()));
            }
            catch (Exception e)
            {
                //do nothing
            }

        }
    }

    /**
     * This method needs to be implemented for downward compatibility
     */
    @Override
    public void destroy()
    {
        //do nothing
    }
}
