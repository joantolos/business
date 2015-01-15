package com.joantolos.spring.mvc.common.security;

public interface Decrypter {
    String decrypt(String encryptedText);
}
