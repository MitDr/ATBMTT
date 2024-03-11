package com.notdemo.nodemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("vigenereRepeatedKey")
public class vigenereRepeatedKey implements ClassicEncryption {

    @Override
    public String encrypt(String plainText, String key) {
        key = generateKey(plainText, key);
        return cipherText(plainText, key);
    }

    @Override
    public String decrypt(String cipherText, String key) {
        key = generateKey(cipherText, key);
        return originalText(cipherText, key);
    }

    private String generateKey(String str, String key){

        int x = str.length();

        for (int i = 0; ; i++) {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key += key.charAt(i);
        }
        return key;
    }
    private String cipherText(String str, String key){
        String cipherText = "";
        for (int i = 0; i < str.length(); i++) {
            char c = (char) (((str.charAt(i) + key.charAt(i)) - 2 * 'A') % 26 + 'A');
            cipherText += c;
        }
        return cipherText;
    }
    private String originalText(String str,  String key){
        String originalText = "";
        for (int i = 0; i < str.length(); i++) {
            char c = (char) (((str.charAt(i) - key.charAt(i) + 26) % 26) + 'A');
            originalText += c;
        }
        return originalText;
    }
}