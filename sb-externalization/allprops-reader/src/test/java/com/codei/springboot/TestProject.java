package com.codei.springboot;

import org.junit.Assert;
import org.junit.Test;

public class TestProject {

    @Test
    public void testAppName() {
        Project project = new Project("Test", null);
        Assert.assertEquals(project.getApplicationName(), "Test");
    }

    @Test
    public void testAppDescription() {
        String appName = "Test";
        Project project = new Project(appName, appName+" description");
        Assert.assertEquals(appName, project.getApplicationName());
        Assert.assertEquals(appName+" description", project.getAppDescription());
    }
}