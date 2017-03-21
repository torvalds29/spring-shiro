package com.sinosoft.shiro.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import java.util.Arrays;

/**
 * Created by oracle on 2017-03-19.
 */
@Configuration
public class JpaConfig {
    @Bean
    FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new OpenEntityManagerInViewFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/"));
        return filterRegistrationBean;
    }
}
