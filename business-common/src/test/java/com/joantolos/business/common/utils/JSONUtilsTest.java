package com.joantolos.business.common.utils;

import com.joantolos.business.common.utils.JSONUtils;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 *  
 * Created by jtolos on 13/01/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/business-common-context-test.xml"})
public class JSONUtilsTest {
    
    @Autowired
    JSONUtils jsonUtils;
    
    private FakePerson fakePerson;
    private String fakePersonJson;
    private ArrayList<Object> fakePersonList;
    private String fakePersonListJson;
    
    @Before
    public void setUp(){
        this.fakePerson = new FakePerson();
        this.fakePerson.setAddress("Some fake address");
        this.fakePerson.setName("Some fake name");
        this.fakePerson.setTelfNumber(1234);
        
        this.fakePersonJson = "{\"name\":\"Some fake name\",\"telfNumber\":1234,\"address\":\"Some fake address\"}";
        
        this.fakePersonList = new ArrayList<>();
        this.fakePersonList.add(this.fakePerson);
        this.fakePersonList.add(this.fakePerson);
        this.fakePersonList.add(this.fakePerson);
        
        this.fakePersonListJson = "[{\"name\":\"Some fake name\",\"telfNumber\":1234,\"address\":\"Some fake address\"},{\"name\":\"Some fake name\",\"telfNumber\":1234,\"address\":\"Some fake address\"},{\"name\":\"Some fake name\",\"telfNumber\":1234,\"address\":\"Some fake address\"}]";
    }
    
    @After
    public void tearDown(){
        this.fakePerson = null;
        this.fakePersonJson = null;
    }

    @Test
    public void objectToJsonTest(){
        String json = this.jsonUtils.objectToJson(this.fakePerson);
        Assert.assertNotNull(json);
    }

    @Test
    public void jsonToObjectTest(){
        FakePerson fakePerson = (FakePerson)this.jsonUtils.jsonToObject(this.fakePersonJson, FakePerson.class);
        Assert.assertNotNull(fakePerson);
        Assert.assertEquals(fakePerson.getAddress(),"Some fake address");
        Assert.assertEquals(fakePerson.getName(), "Some fake name");
        Assert.assertEquals(fakePerson.getTelfNumber(), 1234);
    }

    @Test
    public void objectListJsonToObjectListTest(){
        String fakePersonListJson = this.jsonUtils.objectListToObjectListJson(this.fakePersonList);
        Assert.assertNotNull(fakePersonListJson);
    }

    @Test
    public void objectListToObjectListJsonTest(){
        ArrayList<Object> fakePersonList = this.jsonUtils.objectListJsonToObjectList(this.fakePersonListJson);
        Assert.assertNotNull(fakePersonList);
        Assert.assertNotNull(fakePersonList.get(0));
    }
    
    private class FakePerson {
        private String name;
        private int telfNumber;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getTelfNumber() {
            return telfNumber;
        }

        public void setTelfNumber(int telfNumber) {
            this.telfNumber = telfNumber;
        }
    }
}