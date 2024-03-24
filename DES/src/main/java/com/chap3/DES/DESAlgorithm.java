package com.chap3.DES;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class DESAlgorithm implements DES{
    List<int[][]> sBox = new ArrayList<>();
    @Override
    public String[] generateKey(String key, int[] permutationTable,int [] leftShiftTable, int[] permutationTable2) {
            String bỉnaryKey = hexToBinary(key);
            String permutedKey = permute(bỉnaryKey, permutationTable);
            System.out.println(permutedKey + " " + binaryToHex(permutedKey));
            String C = permutedKey.substring(0, 28);
            String D = permutedKey.substring(28);
            String[] keys = new String[16];
            System.out.println("C[0] " + C + " "+ binaryToHex(C));
            System.out.println("D[0] " + D + " "+ binaryToHex(D));
            for(int i=0;i<leftShiftTable.length;i++){
                C = leftShift(C, leftShiftTable[i]);
                D = leftShift(D, leftShiftTable[i]);
                keys[i] = permute(C+D, permutationTable2);
                System.out.println("C[" + (i+1) + "] " + C + " "+ "D[" + (i+1) + "] " + D + " " +"keys[" + (i+1) + "] " +keys[i] + " " + binaryToHex(keys[i]));
            }
            return keys;
    }

    @Override
    public String encrypt(String[] key, String plainText,int [] IpermutationTable,int [] expansionTable,int[] permutationTable, int[] inverseIpermutationTable) {
        addSBox();
        String binaryText = hexToBinary(plainText);
        String permutedText = permute(binaryText, IpermutationTable);
        String leftHalf = permutedText.substring(0, 32);
        String rightHalf = permutedText.substring(32);
        System.out.println("left:  " + leftHalf + " " +" right: " + rightHalf);
        for(int i=0;i<key.length;i++){
            String temp = expensionPermute(rightHalf,expansionTable);
            //System.out.println("expension: " + temp + " " + binaryToHex(temp));
            String temp2 = xor(key[i], temp);
            //System.out.println("xor: " + temp2 + " " + binaryToHex(temp2));
            StringBuilder temp3 = new StringBuilder();
            for(int j=0;j<8;j++){
                temp3.append(decimaltoBinary(sBoxPermute(temp2.substring(j*6, (j+1)*6),sBox.get(j))));
            }
            String temp4 = permute(temp3.toString(), permutationTable);
            String temp5 = xor(leftHalf, temp4);
            leftHalf = rightHalf;
            rightHalf = temp5;
            System.out.println(binaryToHex(leftHalf) + " " + binaryToHex(rightHalf));
        }
        String swap = binaryToHex(rightHalf) + binaryToHex(leftHalf);
        swap = hexToBinary(swap);
        String output = permute(swap, inverseIpermutationTable);
        return binaryToHex(output);
    }

    private String hexToBinary(String hexString) {
        String binaryString = new BigInteger(hexString, 16).toString(2);
        int length = binaryString.length();
        if (length < 64) {
            binaryString = "0".repeat(64 - length) + binaryString;
        }
        return binaryString;
    }
    private String binaryToHex(String binaryString) {
        return new BigInteger(binaryString, 2).toString(16).toUpperCase();
    }
    private String binaryToDecimal(String binaryString) {
        return new BigInteger(binaryString, 2).toString();
    }
    private String decimaltoBinary(int decimal) {

        String output = Integer.toBinaryString(decimal);

        if(output.length() < 4) {
            output = "0".repeat(4 - output.length()) + output;
        }

        return output;
    }

    private String permute(String input, int[] permutationTable) {
        StringBuilder output = new StringBuilder();
        for(int i=0;i<permutationTable.length;i++){
            output.append(input.charAt(permutationTable[i]-1));
        }
       return output.toString();
    }
    private String expensionPermute(String input, int[] expensionTable) {
        StringBuilder  output = new StringBuilder();
        for(int i=0;i<expensionTable.length;i++){
            output.append(input.charAt(expensionTable[i]-1));
        }
        return output.toString();
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

    private int sBoxPermute(String input, int[][] sBox) {
        String row = input.charAt(0) + "" + input.charAt(5);
        String column = (input.substring(1, 5));
        int rowInt = Integer.parseInt(row, 2);
        int columnInt = Integer.parseInt(column,2);
        return sBox[rowInt][columnInt];
    }
    private String leftShift(String input, int shift) {
        for (int i=0;i<shift;i++){
            String temp = input.substring(0, 1);
            input = input.substring(1) + temp;
        }
        return input ;
    }

    private void addSBox() {
        sBox.add(new int[][] {
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        });
        sBox.add(new int[][]{
                {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
        });
        sBox.add(new int[][]{
                {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
        });
        sBox.add(new int[][]{
                {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
        });
        sBox.add(new int[][]{
                {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
        });
        sBox.add(new int[][]{
                {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
        });
        sBox.add(new int[][]{
                {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
        });
        sBox.add(new int[][]{
                {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
        });
    }
}
