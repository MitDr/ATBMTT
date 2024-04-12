package com.ATBMTT.PUBLICKEYENCRYPTION;

public interface PUBLICKEYENCRYPTION {
    public void DiffysHellman(int q, int a, int xA, int xB);
    public void RSA_1(int p, int q, int e, int M);
    public void RSA_2(int p, int q, int e, int M);
    public void ElGamal(int q, int a, int xA, int k,int M);
    public void DSA(int p , int q, int h, int xA, int k, int HM);
}
