package com.codei.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Project {

    private String applicationName;
    private String appDescription;
    private String appAuthor;

    public Project(@Value("${app.name}") String applicationName,
                   @Value("${app.description}") String appDescription,
                   @Value("${app.author}") String appAuthor) {
        this.applicationName = applicationName;
        this.appDescription = appDescription;
        this.appAuthor = appAuthor;
    }

    String getApplicationName() {
        return applicationName;
    }

    String getAppDescription() {
        return appDescription;
    }

    public String getApplicationAuthor() {
        return appAuthor;
    }
}
