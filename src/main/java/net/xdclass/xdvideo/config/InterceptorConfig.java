package net.xdclass.xdvideo.config;

import net.xdclass.xdvideo.interceptor.Logininterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
///@Configuration
public class InterceptorConfig  implements  WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         //这种不是拦截全部请求吗
        registry.addInterceptor(new Logininterceptor()).addPathPatterns("/**");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
