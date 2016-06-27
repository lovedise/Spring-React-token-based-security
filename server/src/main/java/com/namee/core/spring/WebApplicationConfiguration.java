package com.namee.core.spring;//package com.namee.core.spring;
//
//import com.namee.core.spring.interceptors.CorsInterceptor;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * Created by namee on 2016. 6. 14..
// */
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.namee.api")
//public class WebApplicationConfiguration extends WebMvcConfigurerAdapter {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
////        System.out.printf("aksasdkfjlasjflkdj");
////        registry.addMapping("**").allowedOrigins("http://localhost:9090");
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders()
//                .allowCredentials(true).maxAge(3600);
//    }
//
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(new CorsInterceptor());
////    }
//}
