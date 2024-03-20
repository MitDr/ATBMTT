package com.ATBMTT.Modulo;

import com.ATBMTT.Modulo.EulerValue;
import com.ATBMTT.Modulo.Modulo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("eulerTheorem")
public class EulerTheorem implements Modulo {
    Modulo eulerValue;
    int a,m,n;
    public EulerTheorem(@Value("${a}") int a, @Value("${m}") int m, @Value("${n}") int n) {
        this.a = a;
        this.m = m;
        this.n = n;
        eulerValue = new EulerValue(this.n);
    }

//    public EulerTheorem(int a,int m,int n) {
//        this.a = a;
//        this.m = m;
//        this.n = n;
//        eulerValue = new EulerValue(this.n);
//    }
    @Override
    public int solve() {
        if(Gcd(a,n) != 1){
            return -1;
        }
        if(m == 0){
            return -1;
        }
        int result =1;
        int base = a % n;
        while(m>0){
            if(m % 2 == 1){
                result = (result * base) % n;
            }
            base = (base * base) % n;
            m = m/2;
        }
        return result;
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
}
