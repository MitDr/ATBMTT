package com.ATBMTT.AES;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class AESAlgorithm implements AES{
    @Override
    public String[]  extentKey(String key, String[] RC, String[][] Sbox) {
        String[] word = toWord(key);
        String[] keys = new String[44];
        String[] realKey = new String[11];
        System.arraycopy(word, 0, keys, 0, word.length);
        for(int i=0;i< 4;i++){
            keys[i] = keys[i].toUpperCase();
        }
        String temp = fuctionG(keys[3], RC, Sbox, 0);
        String previousWord = word[0];
        realKey[0] = keys[0]+keys[1]+keys[2]+keys[3];
        for(int i = 1; i < 11; i++) {
            keys[(i)*4] = stringHexaXOR(previousWord, temp);
            keys[(i)*4+1] = stringHexaXOR(keys[(i)*4+1-4], keys[(i)*4]);
            keys[(i)*4+2] = stringHexaXOR(keys[(i)*4+2-4], keys[(i)*4+1]);
            keys[(i)*4+3] = stringHexaXOR(keys[(i)*4+3-4], keys[(i)*4+2]);
            realKey[i] = keys[i*4]+keys[i*4 +1] + keys[i*4 + 2] + keys[i*4 + 3];
            if(i!=10){
                previousWord = fuctionG(keys[(i)*4+3], RC, Sbox, i);
            }
            temp = keys[(i)*4];
        }
        for(int i = 0; i < 11; i++){
            System.out.println(realKey[i]);
        }
        return realKey;
    }

    @Override
    public String encrypt(String plainText, String[] key, String[][] Sbox, String[][] matrix) {
        String start = plainText;
        String temp = stringHexaXOR(plainText, key[0]); //add round key 0
        System.out.println(temp); //add round key 0
        String[] word = toTwoByte(temp, 16);
        StringBuilder output = new StringBuilder();
        for(int i=1;i<11;i++){
            if(i==10){
                System.out.println("Đây là vòng Không có mix column");
                word = toTwoByte(temp, 16);
                output = new StringBuilder();
                for (int j = 0; j < word.length; j++) {
                    output.append(subWord(word[j], Sbox));
                }
                System.out.println(output.toString());
                String[][] input = stringToMatrix(output.toString());
                input = shiftRow(input);
                System.out.println(matrixToString(input));
                System.out.println(stringHexaXOR(matrixToString(input), key[i]));
                String[][] outputmatrix = stringToMatrix(stringHexaXOR(matrixToString(input), key[i]));
                for(int row = 0; row < 4; row++){
                    for(int col = 0; col < 4; col++){
                        System.out.print(outputmatrix[row][col] + " ");
                    }
                    System.out.println();
                }

            }
            else {
                word = toTwoByte(temp, 16);
                output = new StringBuilder();
                for (int j = 0; j < word.length; j++) {
                    output.append(subWord(word[j], Sbox));
                }
                System.out.println(output.toString());
                String[][] input = stringToMatrix(output.toString());
                input = shiftRow(input);
                String[][] tempmatrix = mixColumn(input,matrix);
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 4; col++) {
                        System.out.print(binaryToHex(tempmatrix[row][col]) + " ");
                    }
                    System.out.println();
                }
                System.out.println(matrixToString(tempmatrix));
                System.out.println(binaryToHex(matrixToString(tempmatrix)));
                System.out.println();
                temp = stringHexaXOR(binaryToHex(matrixToString(tempmatrix)), key[i]);
            }
        }
        return "true";
    }

    private String multiply(String a, String b){
       if (a.equals("02") && toBinary(b).charAt(0) == '1'){
            b=toBinary(b);
            b+= "0";
            b = xor(b, "100011011");
            b = b.substring(1);
            b=binaryToHex(b);
        }
        else if(a.equals("02") && toBinary(b).charAt(0) == '0'){
            b = leftShift(toBinary(b),1);
            b=binaryToHex(b);
        }
        else if(a.equals("03")){
           String temp = multiply("02",b);
            b = xor(toBinary(b),toBinary(temp));
            b=binaryToHex(b);
        }
        return b;
    }

    private String xor(String input1, String input2) {
        StringBuilder  output = new StringBuilder();
        for(int i=0;i<input1.length();i++){
            Character first = input1.charAt(i);
            if(first.equals(input2.charAt(i))) {
                output.append("0");
            }else{
                output.append("1");
            }
        }
        return output.toString();
    }
    private String binaryToHex(String binaryString) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < binaryString.length(); i += 4) {
            String chunk = binaryString.substring(i, Math.min(i + 4, binaryString.length()));
            result.append(new BigInteger(chunk, 2).toString(16).toUpperCase());
        }

        return result.toString();
    }

    private String toBinary(String hex){
        String binaryString = new BigInteger(hex, 16).toString(2);
        int length = binaryString.length();
        if (length < 8) {
            binaryString = "0".repeat(8 - length) + binaryString;
        }
        return binaryString;
    }

    private String[][] mixColumn(String[][] input,String[][] matrix){
        String[][] result = new String[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if(k==0){
                        result[i][j] = toBinary(multiply(matrix[i][k], input[k][j]));
                    }
                    else {
                        String temp = toBinary(multiply(matrix[i][k], input[k][j]));
                        result[i][j] = xor(result[i][j], temp);
                    }
                }
            }
        }
        return result;
    }
    private String matrixToString(String[][] input){
        StringBuilder output = new StringBuilder();
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                output.append(input[row][col]);
            }
        }
        return output.toString();
    }
    private String[][] shiftRow(String[][] input) {
        String[][] output = new String[4][4];
        for (int row = 0; row < 4; row++) {
            if(row == 0){
                output[row][0] = input[row][0];
                output[row][1] = input[row][1];
                output[row][2] = input[row][2];
                output[row][3] = input[row][3];
            }
            if(row ==1){
                String temp = input[row][0];
                output[row][0] = input[row][1];
                output[row][1] = input[row][2];
                output[row][2] = input[row][3];
                output[row][3] = temp;
            }
            if(row == 2){
                String temp = input[row][0];
                output[row][0] = input[row][2];
                output[row][1] = input[row][3];
                output[row][2] = temp;
                output[row][3] = input[row][1];
            }
            if(row == 3){
                String temp = input[row][0];
                output[row][0] = input[row][3];
                output[row][1] = temp;
                output[row][2] = input[row][1];
                output[row][3] = input[row][2];
            }
        }
        return output;
    }

    private String leftShift(String input, int shift) {
        for (int i=0;i<shift;i++){
            String temp = input.substring(0, 1);
            input = input.substring(1) + temp;
        }
        return input ;
    }

    public String[][] stringToMatrix(String input) {
        if (input == null || input.length() != 32) {
            throw new IllegalArgumentException("Input string must have length of 32 characters.");
        }

        String[][] matrix = new String[4][4];
        int index = 0;

        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                matrix[row][col] = input.substring(index, index + 2);
                index += 2;
            }
        }
        return matrix;
    }
    private String[] toWord(String key) {
        String[] word = new String[4];
        for(int i = 0; i < 4; i++) {
            word[i] = key.substring(i*8, (i+1)*8);
        }
        return word;
    }

    private String fuctionG(String word, String[] RC,String[][] Sbox, int j) {
        String temp = leftShift(word);
        String[] temp2 = toTwoByte(temp,4);
        for(int i=0;i<temp2.length;i++){
            temp2[i] = subWord(temp2[i], Sbox);
        }
        String temp3 = temp2[0] + temp2[1] + temp2[2] + temp2[3];
        return stringHexaXOR(temp3, RC[j]+"00000000");
    }

    private String leftShift(String word) {
        String temp = word.substring(0,2);
        word = word.substring(2) + temp;
        return word;
    }

    private String[] toTwoByte(String word, int num) {
        String[] output = new String[num];
        for(int i = 0; i < num; i++) {
            output[i] = word.substring(i*2, (i+1)*2);
        }
        return output;
    }

    private int hexToDecimal(String hex){
        return Integer.parseInt(hex, 16);
    }

    private String subWord(String word, String[][] Sbox) {
        return Sbox[hexToDecimal(String.valueOf(word.charAt(0)))][hexToDecimal(String.valueOf(word.charAt(1)))];
    }

    private String stringHexaXOR(String hex1, String hex2){
        StringBuilder output = new StringBuilder();
        for(int i=0;i<hex1.length();i++){
            output.append(hexaXor(String.valueOf(hex1.charAt(i)), String.valueOf(hex2.charAt(i))));
        }
        return output.toString();
    }

    private String hexaXor(String hex1, String hex2) {
        return Integer.toHexString(hexToDecimal(hex1) ^ hexToDecimal(hex2)).toUpperCase();
    }
}
