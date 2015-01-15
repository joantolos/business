package com.joantolos.spring.mvc.common.security;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * Created by jtolos on 15/01/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/webapp-common-context-test.xml"})
public class EncrypterDecrypterTest {
    
    @Autowired
    private Encrypter encrypter;
    
    @Autowired
    private Decrypter decrypter;

    private String somePassword;
    
    @Before
    public void setUp(){
        this.somePassword="1234";
    }
    
    @After
    public void tearDown(){
        this.encrypter=null;
        this.decrypter=null;
    }
    
    @Test
    public void securityTest(){
        String encryptedPassword = this.encrypter.encrypt(this.somePassword);
        Assert.assertNotSame(this.somePassword, encryptedPassword);
        String decryptedPassword = this.decrypter.decrypt(encryptedPassword);
        Assert.assertEquals(this.somePassword,decryptedPassword);
    }
}
