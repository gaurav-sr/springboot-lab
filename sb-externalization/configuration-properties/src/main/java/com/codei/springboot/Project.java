package com.codei.springboot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Project {

    @Autowired
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
}
