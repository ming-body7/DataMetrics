package com.litangwang.metrics.impl;

import com.litangwang.metrics.DataMetricsWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Map;

@Component
@RequestScope
public class DataMetricsWriterImpl implements DataMetricsWriter {

    static Log log = LogFactory.getLog(DataMetricsWriter.class);

    private Map<String, String> logEntries = new HashMap<>();

    @Override
    public void addProperty(String key, String value) {
        logEntries.put(key, value);
    }

    @Override
    public void write() {
        log.info(new JSONObject(logEntries).toString());
    }
}
