package com.ATBMTT.Modulo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("euclid")
public class InverseModuloEuclid implements Modulo{
    int a,n;

    public InverseModuloEuclid(@Value("${a}") int a, @Value("${n}") int n) {
        this.a = a;
        this.n = n;
    }
    @Override
    public int solve() {
        int a1 = 0, b1 = 1, a2 = n, b2 = a;

        if (n == 1)
            System.out.println(0);;

        while (b2 > 1) {

            int q = a2/ b2;

            int tmp1,tmp2;
            tmp1 = a1;
            a1 = b1;
            b1 = tmp1 - q * b1;
            tmp2 = a2;
            a2 = b2;
            b2 = tmp2 - q * b2;

        }

        if (b2 > 1)
            System.out.println("Inverse doesn't exist");
        else {
            if (b1 <= 0)
                return b1 + n;
            else
                return b1;
        }
        return 0;
    }

    @Override
    public int solveRecurr(int a, int m, int n) {
        return 0;
    }
}
