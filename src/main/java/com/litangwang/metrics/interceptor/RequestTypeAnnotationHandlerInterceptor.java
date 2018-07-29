package com.litangwang.metrics.interceptor;

import com.litangwang.metrics.DataMetricsReporter;
import com.litangwang.metrics.annotation.RequestType;
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
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        RequestType requestType = method.getAnnotation(RequestType.class);
        if (requestType == null) {
            return true;
        }
        this.dataMetricsReporter.enableReport();
        this.dataMetricsReporter.setRequestType(requestType.requestType());
        this.dataMetricsReporter.setSubRequestType(requestType.subRequestType());
        return true;
    }

}
