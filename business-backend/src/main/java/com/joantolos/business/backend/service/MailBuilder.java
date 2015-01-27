package com.joantolos.business.backend.service;

import com.joantolos.business.common.entity.BusinessMail;
import com.joantolos.business.common.entity.Mail;
import com.joantolos.business.common.exception.FileManipulationException;

public interface MailBuilder {
    
    public Mail buildMail(BusinessMail businessMail) throws FileManipulationException;
    
}
