package com.codei.springboot;

import org.springframework.stereotype.Component;
import java.io.*;
import java.util.Properties;

@Component
public class AllPropertyLoader {

    private final Properties properties = new Properties();

    public void loadAll() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("myfile.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
