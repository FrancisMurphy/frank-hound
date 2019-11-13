package com.hbfintech.hound.spi;

import com.hbfintech.hound.core.support.HoundContext;
import com.hbfintech.hound.core.support.HoundShepherd;
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
    private HoundContext houndContext = HoundShepherd.getContext();

    /**
     * feign
     */
    @Bean
    public HoundFeignRequestInterceptor houndFeignRequestInterceptor()
    {
        return (HoundFeignRequestInterceptor) houndContext.getBridge("feign");
    }

    /**
     * webMvc
     * @return
     */
    @Bean
    public FilterRegistrationBean houndWebMvcFilterFilterRegistrationBean()
    {
        HoundWebMvcFilter houndFilter = (HoundWebMvcFilter) houndContext.getBridge("mvc");
        FilterRegistrationBean<HoundWebMvcFilter> registrationBean = new FilterRegistrationBean<>(houndFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("houndWebMvcFilter");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

}
