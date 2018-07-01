package com.litangwang.cn.metrics.interceptor;

import com.litangwang.cn.metrics.DataMetricsReporter;
import com.litangwang.cn.metrics.annotation.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class RequestTypeAnnotationHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private DataMetricsReporter dataMetricsReporter;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        RequestType requestType = method.getAnnotation(RequestType.class);
        this.dataMetricsReporter.setRequestType(requestType.requestType());
        this.dataMetricsReporter.setSubRequestType(requestType.subRequestType());
        return true;
    }

}
