package com.notdemo.nodemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("vigenereAutoKey")
public class vigenereAutoKey implements  ClassicEncryption{

    @Override
    public String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyIndex = 0;

        for(char c : plaintext.toCharArray()){
            if(Character.isLetter(c)){
                int shift = key.charAt(keyIndex)-'A';
                ciphertext.append(encryptChar(c, shift));

                key += Character.toUpperCase(c);

                keyIndex = (keyIndex+1) % key.length();
            }
            else{
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }

    @Override
    public String decrypt(String cipherText, String key) {
       StringBuilder plaintext = new StringBuilder();
       int keyIndex = 0;

       for(char c : cipherText.toCharArray()){
           if(Character.isLetter(c)){
               int shift = key.charAt(keyIndex)-'A';
               plaintext.append(decryptChar(c,shift));

               key+=plaintext.charAt(plaintext.length()-1);

               keyIndex = (keyIndex+1) % key.length();
           }
           else {
               plaintext.append(c);
           }
       }
       return plaintext.toString();
    }

    private char encryptChar(char c, int shift){
        if(Character.isUpperCase(c)){
            return (char) (((c - 'A' + shift ) % 26) + 'A');
        } else if (Character.isLowerCase(c)) {
            return (char) (((c - 'a' + shift ) % 26) + 'a');
        }
        return c;
    }

    private char decryptChar(char c, int shift){
        if(Character.isUpperCase(c)){
            return (char) (((c - 'A' - shift + 26) % 26) + 'A');
        } else if (Character.isLowerCase(c)) {
            return (char) (((c - 'a' - shift + 26) % 26) + 'a');
        }
        return c;
    }
}
