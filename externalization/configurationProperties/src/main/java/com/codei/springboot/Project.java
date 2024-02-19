package com.codei.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Project {

    private AppProperties appProperties;

    String getApplicationName() {
        return appProperties.getName();
    }

    String getApplicationAuthor() {
        return appProperties.getAuthor();
    }

    Integer getProjectId() {
        return appProperties.getId();
    }

    @Autowired
    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }
}
