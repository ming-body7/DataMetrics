package com.litangwang.metrics.impl;

import com.litangwang.metrics.DataMetricsReporter;
import com.litangwang.metrics.DataMetricsWriter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Map;

@Component
@RequestScope
public class DataMetricsReporterImpl implements DataMetricsReporter {
    public static final String REQUEST_TYPE = "requestType";
    public static final String SUB_REQUEST_TYPE = "subRequestType";
    public static final String START_TIME = "starTime";
    public static final String END_TIME = "endTime";
    private static final String USER_AGENT = "userAgent";
    private static final String QUERY_STRING = "queryData";
    private static final String USER_ID = "userID";
    private static final String CLIENT_IP = "clientIP";
    private static final String REQUEST_HOST = "requestHost";
    private static final String REQUEST_URI = "requestUri";
    private static final String STATUS_CODE = "statusCode";
    private static final String HTTP_REFERER = "httpReferer";
    private static final String SERVER_IP = "serverIP";
    private static final String O_AUTH_TYPE = "oAuthType";
    private static final String O_AUTH_ID = "oAuthID";
    private static final String APPLICATION_ID_INFO = "applicationIDInfo";
    private static final String INFORMATION = "information";
    private static final String COUNTS = "counts";

    private boolean reportEnabled = false;


    String requestType;
    String subRequestType;

    protected final Map<String, String> informationMap = new HashMap<>();
    long startTime;
    long endTime;

    String userID;
    String clientIP;
    String requestHost;
    String requestUri;
    String userAgent;
    String statusCode;
    String httpCookie;
    String httpReferer;
    String queryString;
    String serverIP;

    /**
     * 第三方授权信息（主要是微信）
     */
    String oAuthType;
    String oAuthID;
    Map<String, String> applicationIDInfo = new HashMap<>();

    /**
     * performance data
     */
    Map<String, Long> times = new HashMap<>();

    /**
     * regular metrics
     */
    Map<String, Integer> counts = new HashMap<>();


    @Autowired
    private DataMetricsWriter dataMetricsWriter;


    @Override
    public void enableReport() {
        this.reportEnabled = true;
    }

    @Override
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public void setSubRequestType(String subRequestType) {
        this.subRequestType = subRequestType;
    }

    @Override
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    @Override
    public void setRequestHost(String requestHost) {
        this.requestHost = requestHost;
    }

    @Override
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    @Override
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setHttpCookie(String httpCookie) {
        this.httpCookie = httpCookie;
    }

    @Override
    public void setHttpReferer(String httpReferer) {
        this.httpReferer = httpReferer;
    }

    @Override
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public void setOAuthType(String oAuthType) {
        this.oAuthType = oAuthType;
    }

    @Override
    public void setOAuthID(String oAuthID) {
        this.oAuthID = oAuthID;
    }

    @Override
    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    @Override
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @Override
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public void addApplicationID(String key, String value) {
        this.applicationIDInfo.put(key, value);
    }

    @Override
    public void addToInformationLine(String key, String value) {
        this.informationMap.put(key, value);
    }

    @Override
    public void addCount(String key, Integer value) {
        this.counts.put(key, value);
    }

    @Override
    public void addTime(String key, Long value) {
        this.times.put(key, value);
    }

    @Override
    public void report() {

        if(!this.reportEnabled) {
            return;
        }
        dataMetricsWriter.addProperty(REQUEST_TYPE, requestType);
        dataMetricsWriter.addProperty(SUB_REQUEST_TYPE, subRequestType);
        dataMetricsWriter.addProperty(START_TIME, String.valueOf(startTime));
        dataMetricsWriter.addProperty(END_TIME, String.valueOf(endTime));
        dataMetricsWriter.addProperty(USER_ID, userID);
        dataMetricsWriter.addProperty(CLIENT_IP, clientIP);
        dataMetricsWriter.addProperty(REQUEST_HOST, requestHost);
        dataMetricsWriter.addProperty(REQUEST_URI, requestUri);
        dataMetricsWriter.addProperty(USER_AGENT, userAgent);
        dataMetricsWriter.addProperty(STATUS_CODE, statusCode);
        dataMetricsWriter.addProperty(HTTP_REFERER, httpReferer);
        dataMetricsWriter.addProperty(QUERY_STRING, queryString);
        dataMetricsWriter.addProperty(SERVER_IP, serverIP);
        dataMetricsWriter.addProperty(O_AUTH_TYPE, oAuthType);
        dataMetricsWriter.addProperty(O_AUTH_ID, oAuthID);
        dataMetricsWriter.addProperty(APPLICATION_ID_INFO, new JSONObject(applicationIDInfo).toString());
        dataMetricsWriter.addProperty(INFORMATION, new JSONObject(informationMap).toString());
        dataMetricsWriter.addProperty(COUNTS, new JSONObject(counts).toString());

        //write to log
        dataMetricsWriter.write();
    }
}
