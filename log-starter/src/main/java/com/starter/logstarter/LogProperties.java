package com.starter.logstarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Map;

@ConfigurationProperties(prefix = "custom-logging")
public class LogProperties {

    private static final Logger log = LoggerFactory.getLogger(LogProperties.class);
    private final Map<String, String> properties;

    @ConstructorBinding
    public LogProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Map<String, String> getLogProperties() {
        return properties;
    }
}
