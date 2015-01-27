package com.joantolos.business.backend.service;

import com.joantolos.business.common.entity.Mail;
import com.joantolos.business.common.exception.FileManipulationException;
import com.joantolos.business.common.exception.MailServiceException;
import com.joantolos.business.common.utils.FileUtils;
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
public class MailServiceTest {

    @Autowired
    private MailSenderService mailService;

    @Autowired
    FileUtils fileUtils;

    @Value("${mail.mail.to.test}")
    private String mailToTest;

    private Mail mail;
    private String samplePdfFile;
    private String htmlMailBody;
    private Logger logger = LoggerFactory.getLogger(MailServiceTest.class);

    @Before
    public void setUp() {
        mail = new Mail();
        mail.setSubject("test email");
        mail.setHtmlContent("Simple test text. Someone is compiling business-backend with TESTS");
        mail.setTo(mailToTest);
        samplePdfFile = "/attachedFile/attachedFile.pdf";
        htmlMailBody = "/mail/templates/successMail.html";

        logger.info("Mail Service test suite executing...");
    }

    @After
    public void tearDown(){
        mailService = null;
        mail = null;
        samplePdfFile = null;
        samplePdfFile = null;
        htmlMailBody = null;
    }

    @Test
    public void sendEmailTest() throws MessagingException, MailServiceException {
        Message message = mailService.sendMail(mail);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.getSubject().equals(mail.getSubject()));

        logger.info("sendEmailTest executed.");
    }

    @Test
    public void sendAttachedFileMail() throws MessagingException, FileManipulationException, MailServiceException {
        byte[] attach = fileUtils.streamToByteArray(this.getClass().getResourceAsStream(samplePdfFile));
        mail.setSubject("Test mail with attachment");
        mail.setHtmlContent("Test mail with attachment. Someone is compiling business-backend with TESTS");
        mail.setAttach(attach);
        mail.setAttachName("someAttachedFile.pdf");

        Message message = mailService.sendMail(mail);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.getSubject().equals(mail.getSubject()));

        logger.info("sendAttachedFileMail executed.");
    }

    @Test
    public void sendHtmlEmailTest() throws MessagingException, FileManipulationException, MailServiceException {
        mail.setHtmlContent(fileUtils.streamToString(this.getClass().getResourceAsStream(htmlMailBody)));
        Message message = mailService.sendMail(mail);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.getSubject().equals(mail.getSubject()));

        logger.info("sendHtmlEmailTest executed.");
    }

    @Test
    public void sendHtmlAttachedFileEmailTest() throws MessagingException, FileManipulationException, MailServiceException {
        byte[] attach = fileUtils.streamToByteArray(this.getClass().getResourceAsStream(samplePdfFile));
        mail.setHtmlContent(fileUtils.streamToString(this.getClass().getResourceAsStream(htmlMailBody)));
        mail.setAttach(attach);
        mail.setSubject("Test mail with attachment html style");
        mail.setAttachName("someAttachedFile.pdf");
        Message message = mailService.sendMail(mail);
        Assert.assertNotNull(message);
        Assert.assertTrue(message.getSubject().equals(mail.getSubject()));

        logger.info("sendHtmlAttachedFileEmailTest executed.");
    }
}
