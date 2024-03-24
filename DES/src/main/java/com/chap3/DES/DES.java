package com.chap3.DES;

import org.springframework.stereotype.Component;

@Component
public interface DES {
    public String[] generateKey(String key, int[] permutationTable,int [] leftShiftTable, int[] permutationTable2);

    public String encrypt(String[] key, String plainText, int[] IpermutationTable, int[] expansionTable,int[] permutationTable,int[] inverseIpermutationTable);
}
