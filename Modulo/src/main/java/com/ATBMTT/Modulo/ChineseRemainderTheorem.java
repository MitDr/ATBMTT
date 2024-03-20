package com.ATBMTT.Modulo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Profile("chineseRemainderTheorem")
public class ChineseRemainderTheorem implements Modulo {
    Modulo modulo1;
    Modulo modulo2;
    Modulo modulo3;
    int a,n,k;
    public ChineseRemainderTheorem(@Value("${a}") int a, @Value("${n}") int n, @Value("${k}") int k) {
        this.a = a;
        this.n = n;
        this.k = k;
    }
    @Override
    public int solve() {
        Set<Integer> factors = priFactors(n);
        Object[] m = factors.toArray();
        int[]  A= new int[m.length];
        int[]  M = new int[m.length];
        int[]  C = new int[m.length];
        for(int i = 0;i<m.length;i++){
            modulo1 = new EulerTheorem(this.a,k, (Integer) m[i]);
            int x = (int)  modulo1.solve();
            if(x == -1){
                modulo2 = new ModuloExponentiation(this.a,k, (Integer) m[i]);
                A[i] = (int) modulo2.solveRecurr(this.a, k, (Integer) m[i]);
                System.out.println("A[" + i + "] = " + A[i]); ;
            }
            A[i] = x;
            System.out.println("A[" + i + "] = " + A[i]);
        }
        for(int i = 0;i<m.length;i++){
            M[i] = 1;
            for (int j= 0;j<m.length;j++){
                if(i!=j){
                    M[i] *= (Integer) m[j];
                }
            }
        }
        for(int i=0;i<m.length;i++){
            modulo3 = new InverseModulo(M[i], (Integer) m[i]);
            C[i] = M[i] * modulo3.solve();
            //System.out.println("C[" + i + "] = " + C[i]);
        }
        int a = 0;
        for(int i=0;i<m.length;i++){
            a += A[i]*C[i];
            //System.out.println(A[i] + " " + C[i] + " ");
        }
        return a % n;
    }

    @Override
    public int solveRecurr(int a, int m, int n) {
        return 0;
    }

    private Set<Integer> priFactors(int n){
        Set<Integer> set = new HashSet<>();
        while(n % 2 == 0){
            set.add(2);
            n /= 2;
        }
        for(int i = 3; i <= Math.sqrt(n); i += 2){
            while(n % i == 0){
                set.add(i);
                n /= i;
            }
        }
        if(n > 2) set.add(n);
        return set;
    }
}
