package io.abover.user.config;

import io.abover.user.interceptor.UserIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MainConfig implements WebMvcConfigurer {
    @Autowired
    private UserIdInterceptor userIdInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(this.userIdInterceptor)
                .addPathPatterns("/v1/users/{*id}/**");
    }
}
