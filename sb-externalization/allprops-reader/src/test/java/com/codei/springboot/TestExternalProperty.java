package com.codei.springboot;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootTest enables Spring Boot features. This works by creating ApplicationContext used in you test
//via SpringApplication. Bootstraps with SpringBoot support.
public class TestExternalProperty {

    @Autowired
    private Project project;

    @Autowired
    private AllPropertyLoader allPropertyLoader;

    @Test
    public void test() {
        Assert.assertNotNull(project);
        String applicationName = project.getApplicationName();
        Assert.assertTrue("TestProject".equals(applicationName));
        Assert.assertTrue("TestProject description".equals(project.getAppDescription()));
    }

    @Test
    public void testAllProperty() {
        allPropertyLoader.loadAll();
        for(String propertyName : allPropertyLoader.getProperties().stringPropertyNames()){
            System.out.println(propertyName);
        }
        Assert.assertTrue(true);
    }

}
