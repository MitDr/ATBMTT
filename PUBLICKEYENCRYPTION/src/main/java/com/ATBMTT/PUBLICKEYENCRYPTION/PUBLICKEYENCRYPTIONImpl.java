package com.ATBMTT.PUBLICKEYENCRYPTION;

import org.springframework.stereotype.Component;

@Component
public class PUBLICKEYENCRYPTIONImpl implements PUBLICKEYENCRYPTION{
    @Override
    public void DiffysHellman(int q, int a, int xA, int xB) {
        int yA = solveRecurr(a, xA, q);
        int yB = solveRecurr(a, xB, q);
        int k = solveRecurr(a, xB * xA, q);
        int kA = solveRecurr(yB, xA, q);
        int kB = solveRecurr(yA, xB, q);
        System.out.println("k = " + k);
        System.out.println("Khóa công khai của An là: yA: "+ yA + " | Khóa phiên là: " + kA);
        System.out.println("Khóa công khai của Ba là: yB: "+ yB + " | Khóa phiên là: " + kB);
    }

    @Override
    public void RSA_1(int p, int q, int e, int M) {
        int N = p *q;
        int phiN = (p - 1) * (q - 1);
        int d = 0;
        System.out.println("PU{"+e+","+N+"}");
        d = inverseModulo(e, phiN);
        System.out.println("PR{"+d+","+N+"}");
        System.out.println("An tạo ra bản ghi bằng cách sử dụng M = "+M + " d và N trong mã bí mật của An");
        int temp =solveRecurr(M,d,N);
        System.out.println(temp);
        System.out.println("Cách người nhận giải mã bản C là sử dụng e,N trong mã công khai của người nhận");
        System.out.println(solveRecurr(temp,e , N));
        System.out.println("Là thực hiện nhiệm vụ chữ kí");
    }

    @Override
    public void RSA_2(int p, int q, int e, int M) {
        int N = p *q;
        int phiN = (p - 1) * (q - 1);
        int d = 0;
        System.out.println("PU{"+e+","+N+"}");
        d = inverseModulo(e, phiN);
        System.out.println("PR{"+d+","+N+"}");
        System.out.println("Cách người gửi Ba mã hóa bản ghi M = "+ M + " để gửi cho an là sử dụng e và N trong mã công khai của An");
        System.out.println(solveRecurr(M,e,N));
        int temp = solveRecurr(M,e,N);
        System.out.println("Cách an giải mã bản C là sử dụng d, N(p*q) trong mã bí mật của An");
        System.out.println(solveRecurr(temp,d,N));
        System.out.println("Là thực hiện nhiệm vụ bảo mật");
    }

    @Override
    public void ElGamal(int q, int a, int xA, int k, int M) {
        int yA  = solveRecurr(a, xA, q);
        System.out.println("PU{"+q+","+a+"," +yA+"}");
        System.out.println("PR{"+xA+"}");
        int K = solveRecurr(yA,k, q);
        int C1 = solveRecurr(a,k,q);
        int C2 = (K*M) % q;
        System.out.println(C1+" " +C2);
        System.out.println("An giải mã: ");
        K = solveRecurr(C1,xA,q);
        M = inverseModulo(K,q) * (C2 % q);
        System.out.println(inverseModulo(K,q) + " " + M);
    }

    @Override
    public void DSA(int p , int q, int h, int xA, int k,int HM) {
        int g = solveRecurr(h,(p-1)/q, p);
        int y = solveRecurr(g,xA,p);
        int r = solveRecurr(g,k,p) % q;
        int s = inverseModulo(k,q) * (HM + r * xA) % q;
        System.out.println("chu ki so: "+ r + " " + s);
        System.out.println("Ba xac minh bang cach ");
        int w = inverseModulo(s,q);
        int u1 = (HM * w) % q;
        int u2 = (r * w) % q;
        int v = (solveRecurr(g,u1,p) * solveRecurr(y,u2,p)) % p;
        System.out.println(v +" " +r);
    }

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

    public int inverseModulo(int a, int n) {
        int a1 = 0, b1 = 1, a2 = n, b2 = a;

        if (n == 1)
            return 0;

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
}
