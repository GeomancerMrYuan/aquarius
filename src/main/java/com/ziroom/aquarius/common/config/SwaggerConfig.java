package com.ziroom.aquarius.common.config;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置类,相当于一个xml文件
 */
@Configuration  //类似@Component,将这个类交给spring容器管理,单例
@EnableSwagger2  //开启swagger注解
//@ConditionalOnProperty(value = "swagger.enable", havingValue = "true")
// springboot特有注解,条件判断,用于环境控制,当swagger.enable=起作用
public class SwaggerConfig{

    @Value("${swagger.enable:true}")
    private Boolean flag;

    /**
     * docket:清单,用于配置swagger
     * 头部信息
     * Swagger开关
     * API分组
     * 扫描接口
     * 设置header(token,cookie,)
     * @return
     */
    @Bean //往容器中注入一个docket实例
    public Docket api() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("X-Auth-Token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                //配置头部信息
                .apiInfo(new ApiInfoBuilder()
                        .title("acquarius")
                        .description("水瓶座的接口文档")
                        .version("v2020.04")
                        .build())
                //API分组
                .groupName("业主分期")
                //配置request_header
//                .globalOperationParameters(pars)
                //Swagger开关
                .enable(flag)
                /**
                 * 匹配api路径
                 * RequestHandlerSelectors:扫描接口路径
                 *  any() // 扫描所有，项目中的所有接口都会被扫描到
                 *  none() // 不扫描接口
                 *  withMethodAnnotation(final Class<? extends Annotation> annotation)  // 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
                 *  withClassAnnotation(final Class<? extends Annotation> annotation) // 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
                 *  basePackage(final String basePackage) // 根据包路径扫描接口
                 * PathSelectors:根据接口路径过滤
                 *  any() // 任何请求都扫描
                 *  none() // 任何请求都不扫描
                 *  regex(final String pathRegex) // 通过正则表达式控制
                 *  ant(final String antPattern) // 通过ant()控制  /**
                 */
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
}
