package com.joantolos.business.backend.service.impl;

import com.joantolos.business.common.entity.Mail;
import com.joantolos.business.common.exception.MailServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.joantolos.business.backend.service.MailSenderService;
import java.util.Properties;

@Component
public class MailSenderServiceImpl implements MailSenderService {

    private Properties props;
    
    @Value("${mail.user.from}")
    private String userFrom;
    @Value("${mail.password.from}")
    private String passwordFrom;
    @Value("${mail.html.charset}")
    private String htmlCharset;
    @Value("${mail.smtp.starttls.enable}")
    private String starttls;

    @PostConstruct
    public void init(){
        props = new Properties();
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public Message sendMail(Mail mail) throws MailServiceException {
        Message message;
        try {
            Session session = getSession();

            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.userFrom));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
            message.setSubject(mail.getSubject());

            Multipart multipart = new MimeMultipart();
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(mail.getHtmlContent(), this.htmlCharset);
            
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
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
                            return new PasswordAuthentication(userFrom, passwordFrom);
                        }
                    });
        }

        return session;
    }
}
