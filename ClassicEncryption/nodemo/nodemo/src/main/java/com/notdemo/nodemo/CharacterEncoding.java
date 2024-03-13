package com.notdemo.nodemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Component
@Profile("CharacterEncoding")
public class CharacterEncoding implements  ClassicEncryption{
    Map<Character,Character> map;


    @Override
    public String encrypt(String plainText, String key) {
        StringBuilder cipherText = new StringBuilder();
        createCharacterEncodingTable(key);
        for (int i = 0; i < plainText.length(); i++) {
            cipherText.append(this.map.get(plainText.charAt(i)));
        }
        return cipherText.toString();
    }

    @Override
    public String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        createCharacterEncodingTable(key);
        for(int i=0;i<cipherText.length();i++){
            char ch = cipherText.charAt(i);
            for(Map.Entry<Character,Character> entry : this.map.entrySet()) {
                if (entry.getValue() == ch) {
                    plainText.append(entry.getKey());
                    break;
                }
            }
        }
        return plainText.toString();
    }

    private void createCharacterEncodingTable(String key){
        this.map = new HashMap<>();

        char[] orinalCharacters = "ABCDEFGHIKLMNOPQRSTUVWXYZ".toCharArray();
        char[] encodedCharacters = key.toCharArray();
        for (int i = 0; i < orinalCharacters.length; i++) {
            this.map.put(orinalCharacters[i], encodedCharacters[i]);
        }
    }
}
