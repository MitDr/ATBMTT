package com.notdemo.nodemo;

public interface ClassicEncryption {
    public String encrypt(String plainText, String key);

    public String decrypt(String cipherText, String key);
}
