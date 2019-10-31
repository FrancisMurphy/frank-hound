package com.hbfintech.hound.spi;

import com.hbfintech.hound.plugin.feign.HoundFeignRequestInterceptor;
import com.hbfintech.hound.plugin.spring.mvc.HoundWebMvcFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author frank
 */
@Configuration
public class HoundAutoConfiguration
{
    /**
     * feign
     */
    @Bean
    public HoundFeignRequestInterceptor houndFeignRequestInterceptor()
    {
        return new HoundFeignRequestInterceptor();
    }

    /**
     * webMvc
     * @return
     */
    @Bean
    public FilterRegistrationBean houndWebMvcFilterFilterRegistrationBean()
    {
        FilterRegistrationBean<HoundWebMvcFilter> registrationBean = new FilterRegistrationBean<>(new HoundWebMvcFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("houndWebMvcFilter");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

}
