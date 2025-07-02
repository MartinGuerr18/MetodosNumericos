package mx.ude.itses.JMTG.MetodosNumericos.domain;

import lombok.Data;

@Data
public class ReglaFalsa {
    private double XL;
    private double XU;
    private double XR;
    private double FXL;
    private double FXU;
    private double FXR;
    private double Ea;
    private double EaPermitido;
    private int iteracionesMaximas;
    private String FX;
}
