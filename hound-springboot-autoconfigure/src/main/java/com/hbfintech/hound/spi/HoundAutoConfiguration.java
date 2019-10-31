package com.hbfintech.hound.spi;


import com.hbfintech.hound.plugin.feign.HoundFeignClientPacker;
import com.hbfintech.hound.plugin.spring.mvc.HoundWebMvcConfigurer;
import com.hbfintech.hound.plugin.spring.mvc.HoundWebMvcInterceptor;
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
    public HoundWebMvcInterceptor mvcTraceInterceptor()
    {
        return new HoundWebMvcInterceptor();
    }

    @Bean
    @ConditionalOnBean(value = { HoundWebMvcInterceptor.class})
    public HoundWebMvcConfigurer mvcWebMvcConfigurer(
            HoundWebMvcInterceptor mvcTraceInterceptor)
    {
        return new HoundWebMvcConfigurer(mvcTraceInterceptor);
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
