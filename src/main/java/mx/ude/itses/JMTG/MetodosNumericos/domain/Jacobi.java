package mx.ude.itses.JMTG.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Jacobi {
    private int MN; // Tamaño del sistema
    private ArrayList<Double> matrizA = new ArrayList<>();
    private ArrayList<Double> vectorB = new ArrayList<>();
    private ArrayList<Double> vectorX = new ArrayList<>();
    private ArrayList<ArrayList<Double>> iteraciones = new ArrayList<>();

    private int maxIteraciones;
    private double tolerancia;

    public boolean esValido() {
        return MN > 0 && !matrizA.isEmpty() && !vectorB.isEmpty();
    }
}
