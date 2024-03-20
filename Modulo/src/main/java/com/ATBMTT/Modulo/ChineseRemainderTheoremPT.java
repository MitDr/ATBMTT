package com.ATBMTT.Modulo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("chineseRemainderTheoremPT")
public class ChineseRemainderTheoremPT implements Modulo{
    int m1,m2,m3;
    int a1,a2,a3;
    Modulo modulo1;
    public ChineseRemainderTheoremPT(@Value("${m1}") int m1, @Value("${m2}") int m2, @Value("${m3}") int m3,
            @Value("${a1}") int a1, @Value("${a2}") int a2, @Value("${a3}") int a3) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }
    @Override
    public int solve() {
        int M = m1 * m2 * m3;
        modulo1 = new InverseModulo(M/m1, m1);
        int C1 =modulo1.solve();
        modulo1 = new InverseModulo(M/m2, m2);
        int C2 = modulo1.solve();
        modulo1 = new InverseModulo(M/m3, m3);
        int C3 = modulo1.solve();
        int x = (a1 * C1 * m2 * m3 + a2 * C2 * m1 * m3 + a3 * C3 * m1 * m2) % M;
        return x;
    }

    @Override
    public int solveRecurr(int a, int m, int n) {
        return 0;
    }

}
