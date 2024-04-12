package com.ATBMTT.Modulo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("euler")
public class EulerValue implements Modulo {
    int n;
//    public EulerValue(@Value("${n}") int n) {
//        this.n = n;
//    }
    public EulerValue(int n) {
        this.n = n;
    }
    @Override
    public int solve() {
        System.out.println(n);
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (areCoprime(n, i)) {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    @Override
    public int solveRecurr(int a, int m, int n) {
        return 0;
    }

    private int Gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public boolean areCoprime(int a, int b) {
        return Gcd(a, b) == 1;
    }
    private boolean checkPrimary(int n){
        if (n == 2 || n == 3)
            return true;
        if (n < 3 || n % 2 == 0 || n % 3 == 0)
            return false;
        for (int i = 5; i * i <= n; i += 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        return true;
    }
}
