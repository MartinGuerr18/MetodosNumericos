package mx.ude.itses.JMTG.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class ReglaCramer {
    private int MN;  // Mantenemos el nombre original para compatibilidad
    private ArrayList<Double> matrizA;
    private ArrayList<Double> vectorB;
    private ArrayList<Double> vectorX;
    private ArrayList<Double> determinantes;  // Corregido a minúscula
    private int NumeroRenglon;  // Mantenemos original para compatibilidad
    
    
    public ReglaCramer() {
        this.matrizA = new ArrayList<>();
        this.vectorB = new ArrayList<>();
        this.vectorX = new ArrayList<>();
        this.determinantes = new ArrayList<>();
        this.NumeroRenglon = 0;
    }
    
    // Método corregido - ahora devuelve el valor DESPUÉS del incremento
    public int IncrementarRenglon() {
        return ++NumeroRenglon;  // Pre-incremento en lugar de post-incremento
    }
    
    // Método de validación simple
    public boolean esValido() {
        return MN >= 2 && MN <= 3
            && matrizA != null && matrizA.size() == MN * MN
            && vectorB != null && vectorB.size() == MN;
    }
}