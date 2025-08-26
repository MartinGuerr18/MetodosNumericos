package mx.ude.itses.JMTG.MetodosNumericos.domain;

import lombok.Data;
import java.util.ArrayList;

@Data
public class DDNewton {
    private ArrayList<Double> puntosX;
    private ArrayList<Double> puntosY;
    private ArrayList<ArrayList<Double>> tablaDiferencias;
    private String polinomio;
    private double valorInterpolado;
    private double puntoInterpolacion;
}