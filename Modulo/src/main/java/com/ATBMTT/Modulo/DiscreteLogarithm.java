package com.ATBMTT.Modulo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("DiscreteLogarithm")
public class DiscreteLogarithm implements Modulo{
    int a;
    int b;
    int n;
    Modulo modulo;
    public DiscreteLogarithm(@Value("${a}") int a, @Value("${b}") int b, @Value("${n}") int n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    @Override
    public int solve() {
        int[] X=new int[n];
        for(int i=1;i<=n-1;i++){
            modulo = new ModuloExponentiation(a,i,n);
            X[i] = modulo.solve();
//            if(X[i]==b){
//                return i;
//            }
        }
        for(int i=1;i<=n-1;i++){
            System.out.println("X["+i+"] = "+X[i]);
        }
        return 0;
    }

    @Override
    public int solveRecurr(int a, int m, int n) {
        return 0;
    }
}
