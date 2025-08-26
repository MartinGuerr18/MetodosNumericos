package mx.ude.itses.JMTG.MetodosNumericos.domain;

import java.util.ArrayList;

public class Gauss {
    private int MN; // dimensión
    private ArrayList<Double> MatrizA;
    private ArrayList<Double> VectorB;
    private ArrayList<Double> vectorX;
    private boolean valido;

    public Gauss() {
        this.MatrizA = new ArrayList<>();
        this.VectorB = new ArrayList<>();
        this.vectorX = new ArrayList<>();
        this.valido = true;
    }

    // Getters y setters
    public int getMN() {
        return MN;
    }

    public void setMN(int MN) {
        this.MN = MN;
    }

    public ArrayList<Double> getMatrizA() {
        return MatrizA;
    }

    public void setMatrizA(ArrayList<Double> matrizA) {
        MatrizA = matrizA;
    }

    public ArrayList<Double> getVectorB() {
        return VectorB;
    }

    public void setVectorB(ArrayList<Double> vectorB) {
        VectorB = vectorB;
    }

    public ArrayList<Double> getVectorX() {
        return vectorX;
    }

    public void setVectorX(ArrayList<Double> vectorX) {
        this.vectorX = vectorX;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }
}
