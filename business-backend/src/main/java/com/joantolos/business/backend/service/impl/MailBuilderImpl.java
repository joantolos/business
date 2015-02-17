package com.joantolos.business.backend.service.impl;

import com.joantolos.business.backend.service.MailBuilder;
import com.joantolos.business.common.entity.BusinessMail;
import com.joantolos.business.common.entity.Mail;
import com.joantolos.utils.exception.FileManipulationException;
import com.joantolos.utils.FileUtils;
import com.joantolos.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailBuilderImpl implements MailBuilder {

    @Value("${mail.templates.success.path}")
    private String templateSuccessPath;
    
    @Value("${mail.templates.error.path}")
    private String templateErrorPath;

    @Value("${mail.attach.file.extension}")
    private String attachFileExtension;

    @Autowired
    FileUtils fileUtils;

    @Autowired
    StringUtils stringUtils;

    public Mail buildMail(BusinessMail businessMail) throws FileManipulationException {
        Mail mail = new Mail();
        mail.setSubject(this.buildSubject(businessMail));
        mail.setTo(businessMail.getUserMailAddress());
        mail.setAttachName(this.buildAttachName(businessMail));
        mail.setHtmlContent(this.buildHtmlContent(businessMail));
        mail.setAttach(businessMail.getAttach());

        return mail;
    }

    private String buildSubject(BusinessMail businessMail) {
        StringBuilder sb = new StringBuilder("Here we have the email about ");
        sb.append(businessMail.getTopic());
        return sb.toString();
    }

    private String buildAttachName(BusinessMail businessMail) {
        StringBuilder sb = new StringBuilder(stringUtils.toCamelCase(businessMail.getTopic()," "));
        sb.append(this.attachFileExtension);

        return sb.toString();
    }

    private String buildHtmlContent(BusinessMail businessMail) throws FileManipulationException {
    	String body = null;
    	
    	if(businessMail.isSuccess()){
	        body = fileUtils.streamToString(this.getClass().getResourceAsStream(this.templateSuccessPath));
    	}else{
    		body = fileUtils.streamToString(this.getClass().getResourceAsStream(this.templateErrorPath));
    		body = body.replace("#errorMessage","ERROR!!");
    	}

        body = body.replace("#userName",businessMail.getUserName());
        
        return body;
    }
}
