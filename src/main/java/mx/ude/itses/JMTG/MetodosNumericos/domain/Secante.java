package mx.ude.itses.JMTG.MetodosNumericos.domain;

import lombok.Data;

@Data
public class Secante {
    private double Xi_1; // xi-1
    private double Xi;   // xi
    private double Xn;   // xi+1
    private double Fxi_1;
    private double Fxi;
    private double Ea;
    private double EaPermitido;
    private int IteracionesMaximas;
    private String FX;
}
