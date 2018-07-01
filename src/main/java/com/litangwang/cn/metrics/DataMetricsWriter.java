package com.litangwang.cn.metrics;

public interface DataMetricsWriter {

    public void addProperty(String key, String value);

    public void write();

}
