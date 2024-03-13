package com.notdemo.nodemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("PermutationEncoding")
public class PermutationEncoding implements ClassicEncryption {
    @Override
    public String encrypt(String plainText, String key) {
        int number;
        if (isInteger(key)) {
            number = Integer.parseInt(key);
        } else {
            number = key.length();
        }
        int numRows = (int) Math.ceil((double) plainText.length() / number);

        char[][] matrix = new char[numRows][number];

        int index = 0;
        for(int i=0;i< numRows;i++){
            for(int j=0;j<number;j++){
                if(index < plainText.length()){
                    matrix[i][j] = plainText.charAt(index++);
                } else{
                    matrix[i][j] = ' ';
                }
            }
        }

        StringBuilder cipherText = new StringBuilder();
        for(int i=0;i<number;i++){
            for(int j=0;j<numRows;j++){
                cipherText.append(matrix[j][i]);
            }
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
        int numRows = (int) Math.ceil((double) cipherText.length() / number);

        char[][] matrix = new char[numRows][number];

        int index = 0;
        for(int j=0;j< number;j++){
            for(int i=0;i<numRows;i++){
                if(index < cipherText.length()){
                    matrix[i][j] = cipherText.charAt(index++);
                } else{
                    matrix[i][j] = ' ';
                }
            }
        }

        StringBuilder plainText = new StringBuilder();
        for(int i=0;i<numRows;i++){
            for(int j=0;j<number;j++){
                plainText.append(matrix[i][j]);
            }
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
