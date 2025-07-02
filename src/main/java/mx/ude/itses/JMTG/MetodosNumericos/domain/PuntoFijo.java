package mx.ude.itses.JMTG.MetodosNumericos.domain;

import lombok.Data;

@Data
public class PuntoFijo {
    private double Xi;
    private double Xn;
    private double GXi;
    private double Ea;
    private double EaPermitido;
    private int IteracionesMaximas;
    private String GX;
}
