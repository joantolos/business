package com.joantolos.business.backend.service.impl;

import com.joantolos.business.common.entity.Mail;
import com.joantolos.utils.FileUtils;
import com.joantolos.utils.exception.FileManipulationException;
import com.joantolos.business.common.exception.MailServiceException;
import com.joantolos.utils.security.Decrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.joantolos.business.backend.service.MailSender;

import java.io.IOException;
import java.util.Properties;

@Component
public class MailSenderImpl implements MailSender {

    private Properties props;
    
    @Value("${mail.user.from}")
    private String userFrom;
    @Value("${mail.password.from}")
    private String passwordFrom;
    @Value("${mail.html.charset}")
    private String htmlCharset;
    @Value("${mail.smtp.starttls.enable}")
    private String starttls;
    @Value("${mail.attach.file.extension}")
    private String attachedFileType;
    
    @Autowired
    private FileUtils fileUtils;
    
    @Autowired
    private Decrypter decrypter;

    @PostConstruct
    public void init(){
        props = new Properties();
        props.put("mail.smtp.starttls.enable", starttls);
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");
    }

    public Message sendMail(Mail mail) throws MailServiceException {
        Message message;
        try {
            Session session = this.getSession();

            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.decrypter.decrypt(this.userFrom)));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
            message.setSubject(mail.getSubject());
            message.setContent(this.createMailContent(mail));

            Transport.send(message);
            
        } catch (MessagingException | IOException | FileManipulationException e) {
            throw new MailServiceException(e.getMessage());
        }

        return message;
    }

    private Session getSession(){
        Session session;

        if (props.getProperty("mail.smtp.starttls.enable").equals("false")){
            session = Session.getInstance(props);
        } else {
            session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(decrypter.decrypt(userFrom), decrypter.decrypt(passwordFrom));
                        }
                    });
        }

        return session;
    }
    
    private Multipart createMailContent(Mail mail) throws MessagingException, IOException, FileManipulationException {        
        // Html part
        Multipart multipart = new MimeMultipart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(mail.getHtmlContent(), this.htmlCharset);

        // Attach part
        if(mail.hasAttach()) {
            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.setFileName(mail.getAttachName());
            attachPart.attachFile(this.fileUtils.byteArrayToFile(mail.getAttach(), mail.getAttachName()));

            multipart.addBodyPart(attachPart);
        }

        multipart.addBodyPart(htmlPart);
        
        return multipart;
    }
}
