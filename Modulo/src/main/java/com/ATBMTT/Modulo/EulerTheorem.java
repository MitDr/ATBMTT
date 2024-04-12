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
//    public EulerTheorem(@Value("${a}") int a, @Value("${m}") int m, @Value("${n}") int n) {
//        this.a = a;
//        this.m = m;
//        this.n = n;
//        eulerValue = new EulerValue(this.n);
//    }

    public EulerTheorem(int a,int m,int n) {
        this.a = a;
        this.m = m;
        this.n = n;
        eulerValue = new EulerValue(this.n);
    }
    @Override
    public int solve() {
        if(Gcd(a,m) != 1){
            eulerValue = new EulerValue(m);
            int phi = eulerValue.solve();
            int temp = m % phi;
            if(temp != 0){
                eulerValue = new ModuloExponentiation(a,temp,n);
                int output = eulerValue.solveRecurr(a,temp,n);
                return output * a;
            }
            else return a;
        }
        if(m == 0){
            return -1;
        }
        int phi = eulerValue.solve();
        int temp = m % phi;
        if(temp != 0){
            eulerValue = new ModuloExponentiation(a,temp,n);

            return eulerValue.solveRecurr(a,temp,n);

        }
        else return 1;
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
