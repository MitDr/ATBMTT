package com.notdemo.nodemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("vigenereAutoKey")
public class vigenereAutoKey implements  ClassicEncryption{
    @Override
    public String encrypt(String plainText, String key) {
        return "dencrypted";
    }

    @Override
    public String decrypt(String cipherText, String key) {
        return "decrypted";
    }
}
