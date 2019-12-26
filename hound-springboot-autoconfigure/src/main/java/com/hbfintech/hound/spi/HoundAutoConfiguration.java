package com.hbfintech.hound.spi;

import com.hbfintech.hound.core.support.Hound;
import com.hbfintech.hound.core.support.Sheepehound;
import com.hbfintech.hound.plugin.feign.HoundFeignRequestInterceptor;
import com.hbfintech.hound.plugin.spring.mvc.HoundWebMvcFilter;
import com.hbfintech.hound.plugin.spring.resttemplate.HoundRestTemplateInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

/**
 * @author frank
 */
@Slf4j
@Configuration
public class HoundAutoConfiguration
{
    private Hound houndContext;

    private Environment environment;

    @Autowired
    public HoundAutoConfiguration(Environment environment)
    {
        this.environment = environment;
        this.houndContext = Sheepehound.getHound();
    }

    /**
     * RestTemplate
     */
    @Bean
    public HoundRestTemplateInterceptor houndRestTemplateInterceptor()
    {
        return (HoundRestTemplateInterceptor) houndContext.getBridge("restTemplate");
    }

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
        try
        {
            HoundWebMvcFilter houndFilter = (HoundWebMvcFilter) houndContext.getBridge("mvc");
            FilterRegistrationBean<HoundWebMvcFilter> registrationBean = new FilterRegistrationBean<>(houndFilter);
            registrationBean.addUrlPatterns("/*");
            registrationBean.setName("houndWebMvcFilter");
            registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
            return registrationBean;
        }
        catch (Exception e)
        {
            log.error("Injection houndWebMvcFilterFilterRegistrationBean error:",e);
            return null;
        }
    }

}
