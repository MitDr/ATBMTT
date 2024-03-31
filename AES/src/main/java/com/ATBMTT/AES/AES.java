package com.ATBMTT.AES;

public interface AES {
    public String[] extentKey(String key, String[] RC, String[][] Sbox);
    public String encrypt(String plainText, String[] key, String[][] Sbox, String[][] matrix);
}
