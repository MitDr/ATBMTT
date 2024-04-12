package com.ATBMTT.Modulo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("B10")
public class B10 implements Modulo{

    int a,b,x,y,n;
    Modulo modulo;

   public B10(@Value("${a}") int a, @Value("${b}") int b, @Value("${x}") int x, @Value("${y}") int y, @Value("${n}") int n){
       this.a = a;
       this.b = b;
       this.x = x;
       this.y = y;
       this.n = n;
   }
    @Override
    public int solve() {
        modulo = new ModuloExponentiation();
        int A1=0;
        int A2=0;
        int A3=0;
        int A4= 0;
        int A5 = 0;
        int x1 = modulo.solveRecurr(a,x,n);
        int x2 = modulo.solveRecurr(b,y,n);
        A1 = (x1 + x2) % n;

        System.out.println("A1 = "+ A1);
        int temp = x1-x2;
        if(temp < 0){
            temp = temp + n;
        }
        A2 = temp % n;
        System.out.println("A2 = "+A2);

        A3 = (x1*x2) %n;
        System.out.println("A3 = "+A3);

        modulo = new InverseModulo(x2,n);
        A4 = modulo.solve();
        System.out.println("A4 = "+A4);

        A5 = (x1*A4) % n;
        System.out.println("A5 = "+A5);
        return 0;
    }

    @Override
    public int solveRecurr(int a, int m, int n) {
        return 0;
    }
}
