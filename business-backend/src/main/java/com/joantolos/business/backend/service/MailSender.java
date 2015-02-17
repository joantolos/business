package com.joantolos.business.backend.service;

import com.joantolos.business.common.entity.Mail;
import com.joantolos.business.common.exception.MailServiceException;

import javax.mail.Message;

public interface MailSender {

    public Message sendMail(Mail mail) throws MailServiceException;
}

