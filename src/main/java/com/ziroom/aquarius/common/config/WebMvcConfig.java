package com.ziroom.aquarius.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc配置类
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2020年04月22日 18:49
 * @since 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 配置资源映射规则
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/").addResourceLocations("/doc.html");
//    }
}
