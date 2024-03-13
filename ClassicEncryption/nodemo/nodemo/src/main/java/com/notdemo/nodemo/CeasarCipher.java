package com.notdemo.nodemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("CeasarCipher")
public class CeasarCipher implements  ClassicEncryption {

    @Override
    public String encrypt(String plainText, String key) {
        int number;
        if (isInteger(key)) {
            number = Integer.parseInt(key);
        } else {
            number = key.length();
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isLowerCase(ch)) {
                    ch = (char) (ch + number);
                    if (ch > 'z') {
                        ch = (char) (ch - 26);
                    }
                } else {
                    ch = (char) (ch + number);
                    if (ch > 'Z') {
                        ch = (char) (ch - 26);
                    }
                }
            }
            cipherText.append(ch);
        }
        return cipherText.toString();
    }



    @Override
    public String decrypt(String cipherText, String key) {
        int number;
        if (isInteger(key)) {
            number = Integer.parseInt(key);
        } else {
            number = key.length();
        }
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isLowerCase(ch)) {
                    ch = (char) (ch - number);
                    if (ch < 'a') {
                        ch = (char) (ch + 26);
                    }
                } else {
                    ch = (char) (ch - number);
                    if (ch < 'A') {
                        ch = (char) (ch + 26);
                    }
                }
            }
            plainText.append(ch);
        }
        return plainText.toString();
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
