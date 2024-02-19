package com.codei.springboot;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Project {

    private final String applicationName;
    private final String appDescription;
    private final String applicationAuthor;

    public Project(@Value("${app.name}") String applicationName,
                   @Value("${app.description}") String appDescription,
                   @Value("${app.author}") String applicationAuthor) {
        this.applicationName = applicationName;
        this.appDescription = appDescription;
        this.applicationAuthor = applicationAuthor;
    }
}
