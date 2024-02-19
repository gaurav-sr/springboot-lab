package com.codei.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Project {

    private String applicationName;
    private String appDescription;

    private Map<String, String> allValues;

    public Project(@Value("${appName}") String applicationName,
                   @Value("${appDescription}") String appDescription) {
        this.applicationName = applicationName;
        this.appDescription = appDescription;
    }

    String getApplicationName() {
        return applicationName;
    }

    String getAppDescription() {
        return appDescription;
    }
}
