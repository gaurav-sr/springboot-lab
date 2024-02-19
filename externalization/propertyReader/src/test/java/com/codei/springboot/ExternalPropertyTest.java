package com.codei.springboot;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExternalPropertyTest {

    @Autowired
    private Project project;

    @Test
    public void test() {
        Assert.assertNotNull(project);
        String applicationName = project.getApplicationName();
        Assert.assertTrue("ExternalApp".equals(applicationName));
        Assert.assertTrue("ExternalApp description".equals(project.getAppDescription()));
    }

}
