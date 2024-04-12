package com.ATBMTT.Modulo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@Profile("exp")
public class ModuloExponentiation implements Modulo{
    int a,m,n;

//    public ModuloExponentiation(@Value("${a}") int a, @Value("${m}") int m, @Value("${n}") int n) {
//        this.a = a;
//        this.m = m;
//        this.n = n;
//    }
    public ModuloExponentiation(int a, int m, int n) {
        this.a = a;
        this.m = m;
        this.n = n;
    }
    public ModuloExponentiation(){

    }

    @Override
    public int solve() {
        int res = 1;
        a = a % n;
        while (m > 0) {
            if ((m & 1) == 1) {
                res = (res * a) % n;
            }
            a = (a * a) % n;
            m /= 2;
        }
        return res;
    }

    @Override
    public int solveRecurr(int a, int m, int n) {
        if (m == 1) {
            return a % n;
        }
        if (m % 2 == 1) {
            return (solveRecurr(a, m - 1, n) * a) % n;
        } else {
            return (int) (Math.pow(solveRecurr(a, m / 2, n),2)) % n;
        }
    }

}
