package com.yinrun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yinrun.interceptor.SysPowerInterceptor;

@EnableWebMvc
@Configuration
@ImportResource(locations = {"classpath:applicationContext.xml"})
public class SystemConfig extends WebMvcConfigurerAdapter
{
    @Bean
    SysPowerInterceptor localInterceptor()
    {
        return new SysPowerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(localInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
