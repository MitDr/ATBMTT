package com.ATBMTT.PUBLICKEYENCRYPTION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PUBLICKEYENCRYPTIONController {
    @Autowired
    PUBLICKEYENCRYPTION publickeyencryption;

    public void DifficultyHellman(int q, int a, int xA, int xB) {
        publickeyencryption.DiffysHellman(q, a, xA, xB);
    }

    public void RSA_1(int q, int p, int e,int M) {
        publickeyencryption.RSA_1(q,p,e,M);
    }

    public void RSA_2(int p, int q, int e, int M) {
        publickeyencryption.RSA_2(p,q,e,M);
    }

    public void ElGamal(int q, int a, int xA, int k, int M) {
        publickeyencryption.ElGamal(q,a,xA,k, M);
    }

    public void DSA(int p , int q, int h, int xA, int k,int HM) {
        publickeyencryption.DSA(p,q,h,xA,k,HM);
    }
}
