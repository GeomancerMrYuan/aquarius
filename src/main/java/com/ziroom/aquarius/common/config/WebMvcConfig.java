package com.ziroom.aquarius.common.config;

import com.ziroom.aquarius.common.resolver.CustomerLocaleResolver;
import com.ziroom.aquarius.system.interceptor.ApiInterceptor;
import com.ziroom.aquarius.system.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年09月04日 16:21
 * @since 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ApiInterceptor apiInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("common/login.html");
        registry.addViewController("/index").setViewName("common/login.html");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new CustomerLocaleResolver();
    }

}
