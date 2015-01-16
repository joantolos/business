package com.joantolos.spring.mvc.common.utils;

import com.joantolos.spring.mvc.common.exception.FileManipulationException;
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

import java.io.*;

/**
 *  
 * Created by jtolos on 13/01/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/business-common-context-test.xml"})
public class FileUtilsTest {
    private Logger logger = LoggerFactory.getLogger(FileUtilsTest.class);
    
    private String fileName;
    private String fileExtension;
    private InputStream inputStream;
    byte[] byteArray;
    
    @Autowired
    FileUtils fileUtils;
    
    @Before
    public void setUp(){
        this.fileName = "testFile";
        this.fileExtension = ".txt";
        this.byteArray = new byte[1024];
        this.inputStream = this.getClass().getResourceAsStream("/testFiles/" + fileName + fileExtension);
        
    }
    
    @After
    public void tearDown() throws FileManipulationException {
        this.fileName = null;
        this.fileExtension = null;
        this.fileUtils.closeInputStream(this.inputStream);
    }

    @Test
    public void byteArrayToInputStreamTest(){
        InputStream inputStream = this.fileUtils.byteArrayToInputStream(this.byteArray);
        Assert.assertNotNull(inputStream);
    }

    @Test
    public void closeInputStreamTest() {
        try {
            InputStream inputStream = this.fileUtils.byteArrayToInputStream(this.byteArray);
            this.fileUtils.closeInputStream(inputStream);
            Assert.assertTrue(true);
        } catch (FileManipulationException e) {
            Assert.fail();
        }
    }

    @Test
    public void closeBufferedReaderTest(){
        String currentLine;
        BufferedReader br;
        
        try {
            br = new BufferedReader(new InputStreamReader(this.inputStream, "UTF-8"));
            int lineCount = 0;
            while ((currentLine = br.readLine()) != null) {
                lineCount++;
            }
            
            if(lineCount>0) Assert.assertTrue(true);
                
            this.fileUtils.closeBufferedReader(br);
            Assert.assertTrue(true);
        } catch (IOException e) {
            Assert.fail();
        } catch (FileManipulationException e) {
            Assert.fail();
        }
    }

    @Test
    public void streamToStringTest() throws FileManipulationException {
        String string = this.fileUtils.streamToString(this.inputStream);
        logger.debug("File content on string form: " + string);
    }

    @Test
    public void streamToByteArrayTest() throws FileManipulationException {
        byte[] byteArray = this.fileUtils.streamToByteArray(this.inputStream);
        Assert.assertNotNull(byteArray);
    }

    @Test
    public void stringToInputStreamTest() throws FileManipulationException {
        InputStream inputStream = this.fileUtils.stringToInputStream(this.fileUtils.streamToString(this.inputStream));
        Assert.assertNotNull(inputStream);
    }

    @Test
    public void writeFileTest() throws FileManipulationException {
        String tempFilePath = "C:/temp/test.txt";
        this.fileUtils.writeFile(this.byteArray, tempFilePath);
        Assert.assertTrue(this.fileExistTest(tempFilePath));
        this.deleteFileTest(tempFilePath);
        Assert.assertFalse(this.fileExistTest(tempFilePath));
    }
    
    private void deleteFileTest(String tempFilePath){
        this.fileUtils.deleteFile(tempFilePath);
    }
    
    private boolean fileExistTest(String tempFilePath){
        return this.fileUtils.fileExist(tempFilePath);
    }
    
}
