package com.hejianji.mall;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用来现在什么地址该阻止，什么地址该放行
 */
@Configuration  //只有添加了这个注解，这个类才起作用
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/user/login","/user/register","/categories");


//        WebMvcConfigurer.super.addInterceptors(registry);

    }
}
