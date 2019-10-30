package com.hbfintech.hound.spi;


import com.hbfintech.hound.plugin.feign.HoundFeignClientPacker;
import com.hbfintech.hound.plugin.spring.mvc.configurer.HoundMvcConfigurer;
import com.hbfintech.hound.plugin.spring.mvc.interceptor.MvcTraceInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author frank
 */
@Configuration
public class HoundAutoConfiguration
{
    /**
     * MVC
     */
    @Bean
    public MvcTraceInterceptor mvcTraceInterceptor()
    {
        return new MvcTraceInterceptor();
    }

    @Bean
    @ConditionalOnBean(value = {MvcTraceInterceptor.class})
    public HoundMvcConfigurer mvcWebMvcConfigurer(MvcTraceInterceptor mvcTraceInterceptor)
    {
        return new HoundMvcConfigurer(mvcTraceInterceptor);
    }

    /**
     * feign
     */
    @Bean
    public HoundFeignClientPacker feignTraceInterceptor()
    {
        return new HoundFeignClientPacker();
    }

}
