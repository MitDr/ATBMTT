package com.notdemo.nodemo;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncryptionController {
    @Autowired
    ClassicEncryption encryption;


    public String encrypt(String plainText, String key) {
        return encryption.encrypt(plainText, key);
    }

    public String decrypt(String cipherText, String key) {
        return encryption.decrypt(cipherText, key);
    }
}
