package mx.ude.itses.JMTG.MetodosNumericos.services;

import mx.ude.itses.JMTG.MetodosNumericos.domain.DDNewton;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Lagrange;

public interface UnidadIVService {
    public DDNewton AlgoritmoDDNewton(DDNewton modelNewton);
    public Lagrange AlgoritmoLagrange(Lagrange modelLagrange);
}