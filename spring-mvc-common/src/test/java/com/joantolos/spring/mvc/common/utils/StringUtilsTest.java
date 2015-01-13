package com.joantolos.spring.mvc.common.utils;

import com.joantolos.spring.mvc.common.exception.StringManipulationException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 *  
 * Created by u6019943 on 13/01/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring-mvc-common-context-test.xml"})
public class StringUtilsTest {

    private Logger logger = LoggerFactory.getLogger(StringUtilsTest.class);

    @Autowired
    StringUtils stringUtils;
    
    private String test;
    private String[] stringArray;

    @Before
    public void setUp(){
        this.test = "some text with no format what however";

        stringArray = new String[2];
        stringArray[0] = "first element";
        stringArray[1] = "second element";
    }

    @After
    public void tearDown(){
        this.test = null;
    }

    @Test
    public void toCamelCaseTest(){
        String manipulatedString = this.stringUtils.toCamelCase(this.test, " ");
        logger.info(manipulatedString);
    }

    @Test
    public void toProperCaseTest(){
        String manipulatedString = this.stringUtils.toProperCase(this.test);
        logger.info(manipulatedString);
    }

    @Test
    public void firstLetterLowerCaseTest(){
        String manipulatedString = this.stringUtils.firstLetterLowerCase(this.stringUtils.toProperCase(this.test));
        logger.info(manipulatedString);
    }

    @Test
    public void stringArrayToArrayListTest(){
        ArrayList<String> arrayList = this.stringUtils.stringArrayToArrayList(this.stringArray);
        Assert.assertNotNull(arrayList);
        Assert.assertEquals(arrayList.get(0), "first element");
        Assert.assertEquals(arrayList.get(1), "second element");
    }

    @Test
    public void replaceLastChars() throws StringManipulationException {
        String manipulatedString = this.stringUtils.replaceLastChars(this.test,"whatever",7);
        Assert.assertEquals(manipulatedString, "some text with no format what whatever");
    }
}
