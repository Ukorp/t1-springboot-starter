package com.starter.logstarter;

import org.log.CustomLoggingConfiguration;
import org.log.aspect.LogAspect;
import org.log.aspect.TimeMonitoringAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(name = "custom-logging.enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {

    private final LogProperties logProperties;

    public LogAutoConfiguration(LogProperties logProperties) {
        this.logProperties = logProperties;
    }

    @Bean
    public LogAspect logAspect(CustomLoggingConfiguration config) {
        return new LogAspect(config);
    }

    @Bean
    public TimeMonitoringAspect timeMonitoringAspect(CustomLoggingConfiguration config) {
        return new TimeMonitoringAspect(config);
    }

    @Bean
    public CustomLoggingConfiguration customLoggingConfiguration() {
        Map<String, String> props = logProperties.getLogProperties();
        if (props == null) {
            return new CustomLoggingConfiguration();
        }
        return new CustomLoggingConfiguration(props);
    }
}
