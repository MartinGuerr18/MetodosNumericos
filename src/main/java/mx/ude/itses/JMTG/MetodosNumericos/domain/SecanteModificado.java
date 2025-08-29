package mx.ude.itses.JMTG.MetodosNumericos.domain;

import lombok.Data;

@Data
public class SecanteModificado {
     private double xiAnterior;
    private double xi;
    private double fxAnterior;
    private double fx;
    private double xr;
    private double ea;
    private int iteracion;
    private int iteracionesMaximas;
    private double tolerancia;
    private String funcion;
    private double delta;
}
