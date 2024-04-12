package com.ATBMTT.Modulo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("inv")
public class InverseModulo implements Modulo {
    int a,n;

//    public InverseModulo(@Value("${a}") int a, @Value("${n}") int n) {
//        this.a = a;
//        this.n = n;
//    }
    public InverseModulo(int a, int n) {
        this.a = a;
        this.n = n;
    }

    @Override

    public int solve() {

        int res = 0;
        a = a % n;
        for (int i = 1; i < n; i++) {
            if ((a * i) % n == 1) {
                res = i;
                break;
            }
        }
        return res;
    }

    @Override
    public int solveRecurr(int a, int m, int n) {
        return 0;
    }
}
