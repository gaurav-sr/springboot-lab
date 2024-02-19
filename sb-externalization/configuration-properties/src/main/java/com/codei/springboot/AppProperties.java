package com.codei.springboot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppProperties {
    private String name;
    private Integer id;
    private String author;
}
