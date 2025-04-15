package org.log;

import org.slf4j.event.Level;

import java.util.HashMap;
import java.util.Map;

public class CustomLoggingConfiguration {

    private final Map<String, Level> levelMap;

    public CustomLoggingConfiguration() {
        levelMap = new HashMap<>();
        levelMap.put("throw", Level.ERROR);
        levelMap.put("return", Level.INFO);
        levelMap.put("before", Level.INFO);
        levelMap.put("monitoring", Level.INFO);
    }

    public CustomLoggingConfiguration(Map<String, String> stringMap) {
        this.levelMap = new HashMap<>();
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            this.levelMap.put(entry.getKey(), getLevelFromString(entry.getValue()));
        }
    }

    public static Level getLevelFromString(String level) {
        return switch (level.toLowerCase()) {
            case "error" -> Level.ERROR;
            case "warn" -> Level.WARN;
            case "debug" -> Level.DEBUG;
            case "trace" -> Level.TRACE;
            default -> Level.INFO;
        };
    }

    public Level getLevelThrow() {
        return levelMap.getOrDefault("throw", Level.ERROR);
    }

    public Level getLevelReturn() {
        return levelMap.getOrDefault("return", Level.INFO);
    }

    public Level getLevelBefore() {
        return levelMap.getOrDefault("before", Level.INFO);
    }

    public Level getLevelMonitoring() {
        return levelMap.getOrDefault("monitoring", Level.INFO);
    }
}
