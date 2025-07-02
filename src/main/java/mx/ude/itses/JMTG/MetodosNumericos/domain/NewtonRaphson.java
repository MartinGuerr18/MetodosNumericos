package mx.ude.itses.JMTG.MetodosNumericos.domain;

import lombok.Data;

@Data
public class NewtonRaphson {
    private double Xi;
    private double Xn;
    private double Fxi;
    private double Fdxi;
    private double Ea;
    private double EaPermitido;
    private int IteracionesMaximas;
    private String FX;   // función f(x)
    private String dFX;  // derivada f'(x)
}
