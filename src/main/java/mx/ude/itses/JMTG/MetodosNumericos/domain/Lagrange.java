package mx.ude.itses.JMTG.MetodosNumericos.domain;

import lombok.Data;
import java.util.ArrayList;

@Data
public class Lagrange {
    private ArrayList<Double> puntosX;
    private ArrayList<Double> puntosY;
    private String polinomio;
    private double valorInterpolado;
    private double puntoInterpolacion;
}