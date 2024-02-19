package com.codei.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Project {

    private final String applicationName;
    private final String appDescription;

    /**
     * Constructor with arguments whose values are injected from application.properties.
     *
     * @param applicationName injected from application.properties
     * @param appDescription injected from application.properties
     */
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
