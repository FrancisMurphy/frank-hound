package com.hbfintech.hound.plugin.spring.mvc;

import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author frank
 */
public class HoundWebMvcConfigurer implements WebMvcConfigurer
{
    private HoundWebMvcInterceptor mvcTraceInterceptor;

    public HoundWebMvcConfigurer()
    {
    }

    public HoundWebMvcConfigurer(
            HoundWebMvcInterceptor mvcTraceInterceptor)
    {
        this.mvcTraceInterceptor = mvcTraceInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(mvcTraceInterceptor);
        addInterceptor.addPathPatterns("/**");
    }
}
