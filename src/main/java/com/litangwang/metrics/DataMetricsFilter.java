package com.litangwang.metrics;

import com.litangwang.metrics.helper.DataMetricsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DataMetricsFilter extends OncePerRequestFilter {

    @Autowired
    private DataMetricsReporter dataMetricsReporter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        dataMetricsReporter.setStartTime(System.currentTimeMillis());
        dataMetricsReporter.setUserID("");  //TODO: integrate with user service
        dataMetricsReporter.setClientIP(DataMetricsHelper.getClientIP(request));
        dataMetricsReporter.setRequestHost(request.getRemoteHost());
        dataMetricsReporter.setRequestUri(request.getRequestURI());
        dataMetricsReporter.setUserAgent(DataMetricsHelper.getUserAgent(request));
        dataMetricsReporter.setHttpCookie(DataMetricsHelper.getCookies(request));
        dataMetricsReporter.setHttpReferer(DataMetricsHelper.getHttpReferer(request));
        dataMetricsReporter.setQueryString(DataMetricsHelper.getQueryString(request));
        dataMetricsReporter.setServerIP(request.getRemoteAddr());

        filterChain.doFilter(request, response);

        dataMetricsReporter.setEndTime(System.currentTimeMillis());
        dataMetricsReporter.setStatusCode(String.valueOf(response.getStatus()));
        dataMetricsReporter.report();
    }
}
