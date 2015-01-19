package com.joantolos.business.common.security.impl;

import com.joantolos.business.common.security.Encrypter;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncrypterImpl implements Encrypter {

    @Value("${security.key}")
    private String securityKey;

    public String encrypt(String plainText) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(this.securityKey+new StringBuffer(this.securityKey).reverse().toString());
        return textEncryptor.encrypt(plainText);
    }
}
