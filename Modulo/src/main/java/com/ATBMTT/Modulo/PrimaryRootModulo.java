package com.ATBMTT.Modulo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Profile("primaryRoot")
public class PrimaryRootModulo implements Modulo{
    int n;
    int a;
    Modulo modulo;
    public PrimaryRootModulo(@Value("${a}") int a,@Value("${n}") int n) {
        this.a = a;
        this.n = n;
    }
    @Override
    public int solve() {
        modulo = new EulerValue(n);
        List<Integer> divisors = findDivisors(modulo.solve());
        int[] X = new int[divisors.size()];

//        System.out.println(n);
        for (int i = 0; i < divisors.size(); i++) {
            modulo = new ModuloExponentiation(a,divisors.get(i),n);
            X[i] = modulo.solve();
            System.out.println("X[" + divisors.get(i) + "] = " + X[i]);
        }
        for(int i=0;i<divisors.size()-1;i++){
            if(X[i] == 1){
                return -1;
            }
        }
        if(X[X.length-1] != 1){
            return -1;
        }
        return 1;
    }

    @Override
    public int solveRecurr(int a, int m, int n) {
        return 0;
    }

    private List<Integer> findDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i != n / i) {
                    divisors.add(n / i);
                }
            }
        }
        Collections.sort(divisors);
        Collections.reverse(divisors);
        return divisors;
    }
}
