package com.notdemo.nodemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("playFair")
public class PlayFair implements ClassicEncryption {
    @Override
    public String encrypt(String plainText, String key) {
        key = key.replaceAll("[^a-zA-Z]", "").toUpperCase();
        key = key.replace("J", "I");
        char[][] table = generateTable(key);
        //printMatrix(table);
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i += 2) {
            char firstChar = plainText.charAt(i);
            char secondChar = (i + 1 < plainText.length()) ? plainText.charAt(i + 1) : 'X';

            if(firstChar == secondChar){
                secondChar = 'X';
                i--;
            }
            int[] firstPosition = findPosition(firstChar, table);
            int[] secondPosition = findPosition(secondChar, table);

            if (firstPosition[0] == secondPosition[0]) {
                cipherText += table[firstPosition[0]][(firstPosition[1] + 1) % 5];
                cipherText += table[secondPosition[0]][(secondPosition[1] + 1) % 5];
            } else if (firstPosition[1] == secondPosition[1]) {
                cipherText += table[(firstPosition[0] + 1) % 5][firstPosition[1]];
                cipherText += table[(secondPosition[0] + 1) % 5][secondPosition[1]];
            } else {
                cipherText += table[firstPosition[0]][secondPosition[1]];
                cipherText += table[secondPosition[0]][firstPosition[1]];
            }
        }
        return cipherText;
    }

    @Override
    public String decrypt(String cipherText, String key) {
        cipherText = cipherText.replaceAll("[^a-zA-Z]", "").toUpperCase();
        StringBuilder decryptedText = new StringBuilder();
        for(int i = 0; i < cipherText.length(); i += 2){
            char firstChar = cipherText.charAt(i);
            char secondChar = (i + 1 < cipherText.length()) ? cipherText.charAt(i + 1) : 'X';

            int[] firstPosition = findPosition(firstChar, generateTable(key));
            int[] secondPosition = findPosition(secondChar, generateTable(key));

            if(firstPosition[0] == secondPosition[0]){
                decryptedText.append(generateTable(key)[firstPosition[0]][(firstPosition[1] + 4) % 5]);
                decryptedText.append(generateTable(key)[secondPosition[0]][(secondPosition[1] + 4) % 5]);
            } else if (firstPosition[1] == secondPosition[1]) {
                decryptedText.append(generateTable(key)[(firstPosition[0] + 4) % 5][firstPosition[1]]);
                decryptedText.append(generateTable(key)[(secondPosition[0] + 4) % 5][secondPosition[1]]);
            }
            else{
                decryptedText.append(generateTable(key)[firstPosition[0]][secondPosition[1]]);
                decryptedText.append(generateTable(key)[secondPosition[0]][firstPosition[1]]);
            }
        }
        return decryptedText.toString();
    }

    private int[] findPosition(char ch, char[][] table) {
        int[] position = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (table[i][j] == ch) {
                    position[0] = i;
                    position[1] = j;
                    break;
                }
            }
        }
        return position;
    }

    private char[][] generateTable(String key) {

        char[][] matrix = new char[5][5];
        int index = 0;
        boolean[] used = new boolean[26];

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!used[ch - 'A'] && ch != ' ') {
                matrix[index / 5][index % 5] = ch;
                used[ch - 'A'] = true;
                index++;
            }
        }
        char currentChar = 'A';
        for (int i = index; i < 25; i++) {
            while (used[currentChar - 'A'] || currentChar == 'J') {
                currentChar++;
            }
            matrix[i / 5][i % 5] = currentChar;
            used[currentChar - 'A'] = true;
            currentChar++;
        }
        return matrix;
    }

    public void printMatrix(char[][] matrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
