package com.litangwang.metrics;

public interface DataMetricsReporter {

    public void enableReport();

    public void setRequestType(String requestType);
    public void setSubRequestType(String subRequestType);
    public void setUserID(String userID);
    public void setClientIP(String clientIP);
    public void setRequestHost(String requestHost);
    public void setRequestUri(String requestUri);
    public void setUserAgent(String userAgent);
    public void setStatusCode(String statusCode);
    public void setHttpCookie(String httpCookie);
    public void setHttpReferer(String httpReferer);
    public void setQueryString(String queryString);
    public void setServerIP(String serverIP);

    public void setStartTime(Long startTime);
    public void setEndTime(Long endTime);

    /**
     * 用户Identity相关，例如微信ID等
     * @param key
     * @param value
     */
    public void addApplicationID(String key, String value);

    /**
     * 请求相关，需要专门收集的业务数据
     * @param key
     * @param value
     */
    public void addToInformationLine(String key, String value);

    /**
     * 业务相关数据量（例如，点击次数等）
     * @param key
     * @param value
     */
    public void addCount(String key, Integer value);

    /**
     * 性能相关数据，如API延迟等
     * @param key
     * @param value
     */
    public void addTime(String key, Long value);

    public void report();

}
