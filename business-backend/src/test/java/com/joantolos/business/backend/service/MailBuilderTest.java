package com.joantolos.business.backend.service;

import com.joantolos.business.common.entity.BusinessMail;
import com.joantolos.business.common.entity.Mail;
import com.joantolos.utils.FileUtils;
import com.joantolos.utils.exception.FileManipulationException;
import com.joantolos.business.common.exception.MailServiceException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Message;
import javax.mail.MessagingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/business-backend-context-test.xml"})
public class MailBuilderTest {

    @Autowired
    private MailBuilder mailBuilder;
    
    @Autowired
    private MailSender mailSender;

    @Autowired
    FileUtils fileUtils;

    @Value("${mail.mail.to.test}")
    private String mailToTest;

    private BusinessMail businessMail;
    private Logger logger = LoggerFactory.getLogger(MailBuilderTest.class);

    @Before
    public void setUp() throws FileManipulationException {
        this.businessMail = new BusinessMail();
        this.businessMail.setAttach(this.fileUtils.streamToByteArray(this.getClass().getResourceAsStream("/attachedFile/attachedFile.pdf")));
        this.businessMail.setMailDate("01/01/2015");
        this.businessMail.setMailId(new Long(1));
        this.businessMail.setTopic("our super product");
        this.businessMail.setUserMailAddress(this.mailToTest);
        this.businessMail.setUserName("Joan Tolós");

        logger.info("Mail Builder test suite executing...");
    }

    @After
    public void tearDown(){
        this.mailBuilder = null;
        this.mailSender = null;
        this.businessMail = null;
        this.mailToTest = null;
    }

    @Test
    public void sendBusinessSuccessEmailNoAttachTest() throws MessagingException, MailServiceException, FileManipulationException {
        this.businessMail.setSuccess(true);
        Mail mail = this.mailBuilder.buildMail(this.businessMail);
        mail.setAttach(null);
        mail.setAttachName(null);
        Message message = this.mailSender.sendMail(mail);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.getSubject().equals(mail.getSubject()));

        logger.info("sendBusinessSuccessEmailNoAttachTest executed.");
    }

    @Test
    public void sendBusinessErrorEmailNoAttachTest() throws MessagingException, MailServiceException, FileManipulationException {
        this.businessMail.setSuccess(false);
        Mail mail = this.mailBuilder.buildMail(this.businessMail);
        mail.setAttach(null);
        mail.setAttachName(null);
        Message message = this.mailSender.sendMail(mail);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.getSubject().equals(mail.getSubject()));

        logger.info("sendBusinessErrorEmailNoAttachTest executed.");
    }

    @Test
    public void sendBusinessSuccessEmailTest() throws MessagingException, MailServiceException, FileManipulationException {
        this.businessMail.setSuccess(true);
        Mail mail = this.mailBuilder.buildMail(this.businessMail);
        Message message = this.mailSender.sendMail(mail);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.getSubject().equals(mail.getSubject()));

        logger.info("sendBusinessSuccessEmailTest executed.");
    }

    @Test
    public void sendBusinessErrorEmailTest() throws MessagingException, MailServiceException, FileManipulationException {
        this.businessMail.setSuccess(false);
        Mail mail = this.mailBuilder.buildMail(this.businessMail);
        Message message = this.mailSender.sendMail(mail);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.getSubject().equals(mail.getSubject()));

        logger.info("sendBusinessErrorEmailTest executed.");
    }
}
