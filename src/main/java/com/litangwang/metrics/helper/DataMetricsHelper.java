package com.litangwang.metrics.helper;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class DataMetricsHelper {

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getHttpReferer(HttpServletRequest request){
        final String referer = request.getHeader("referer");
        return referer;
    }

    public static String getQueryString(HttpServletRequest request) {
        if ("POST" == request.getMethod()) {
            JSONObject jsonObj = new JSONObject();
            Map<String,String[]> params = request.getParameterMap();
            for (Map.Entry<String,String[]> entry : params.entrySet()) {
                String v[] = entry.getValue();
                Object o = (v.length == 1) ? v[0] : v;
                try {
                    jsonObj.put(entry.getKey(), o);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return jsonObj.toString();
        }
        return request.getQueryString();
    }

    public static String getCookies(HttpServletRequest request) {
        return "";  //TODO: add cookie string
    }
}
