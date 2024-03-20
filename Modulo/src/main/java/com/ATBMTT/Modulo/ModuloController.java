package com.ATBMTT.Modulo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModuloController {
    @Autowired()
    Modulo modulo;

    public ModuloController(Modulo modulo) {
        this.modulo = modulo;
    }
    public int solve() {
        return modulo.solve();
    }
    public int solveRecurr(int a, int m, int n) {
        return modulo.solveRecurr(a, m, n);
    }
}
