package com.codei.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExternalPropertyTest {

    @Autowired
    private Project project;

    @Test
    public void test() {
        Assertions.assertNotNull(project);
        String applicationName = project.getApplicationName();
        Assertions.assertEquals("ExternalApp", applicationName);
        Assertions.assertEquals("ExternalApp description", project.getAppDescription());
    }

}
