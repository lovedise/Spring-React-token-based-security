package com.namee.core.spring.interceptors;//package com.namee.core.spring.interceptors;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by namee on 2016. 6. 14..
// */
//@Slf4j
//public class CorsInterceptor implements HandlerInterceptor {
//    private static final String  ACCESS_CONTROL_ALLOW_ORIGIN      = "Access-Control-Allow-Origin";
//    private static final String  ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
//    private static final String  REQUEST_HEADER_ORIGIN            = "Origin";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.debug("preHandle() start");
//        String origin = request.getHeader(REQUEST_HEADER_ORIGIN);
//
//        log.debug("Origin Header: {}", origin);
//
//        if(StringUtils.hasLength(origin)) {
//            response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
//            response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//        }
//
//        log.debug("preHandle() end");
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
