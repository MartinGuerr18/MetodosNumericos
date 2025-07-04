package mx.ude.itses.JMTG.MetodosNumericos.domain;

import lombok.Data;

@Data
public class SecanteModificado {
    private double Xi;
    private double Xn;
    private double Fxi;
    private double dFXi;
    private double Ea;
    private double EaPermitido;
    private int IteracionesMaximas;
    private String FX;
}
