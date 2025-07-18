package com.bxp.MaysTech_Spring.config;

import com.bxp.MaysTech_Spring.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegister() {
        FilterRegistrationBean<JwtFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(jwtFilter);
        reg.addUrlPatterns("/users/*", "/user-product/*"); // bảo vệ API cần login
        return reg;
    }

    // CORS config
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // cho tất cả origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // cho phép mấy method này
                .allowedHeaders("*"); // cho gửi bất kỳ header nào
    }
}
