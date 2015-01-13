package com.joantolos.spring.mvc.common.utils;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *  
 * Created by u6019943 on 13/01/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring-mvc-common-context-test.xml"})
public class FileUtilsTest {
    
    @Autowired
    FileUtils fileUtils;
    
    @Before
    public void setUp(){
        
    }
    
    @After
    public void tearDown(){
                
    }
    
    @Test
    public void openFileTest(){
        Assert.assertTrue(true);
    }
}
