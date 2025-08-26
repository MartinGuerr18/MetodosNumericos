package mx.ude.itses.JMTG.MetodosNumericos.domain;

import java.util.Arrays;

public class GaussJordan {
    private int MN;                 // Tamaño del sistema (2, 3 o 4)
    private double[] MatrizA;       // Coeficientes de la matriz A en forma lineal
    private double[] VectorB;       // Vector B
    private double[] vectorX;       // Soluciones
    private boolean valido;         // Indica si el sistema tiene solución válida

    public GaussJordan() {}

    public GaussJordan(int MN, double[] MatrizA, double[] VectorB) {
        this.MN = MN;
        this.MatrizA = MatrizA;
        this.VectorB = VectorB;
        this.vectorX = new double[MN];
        this.valido = true;
    }

    // Getters y Setters
    public int getMN() { return MN; }
    public void setMN(int MN) { this.MN = MN; }

    public double[] getMatrizA() { return MatrizA; }
    public void setMatrizA(double[] matrizA) { MatrizA = matrizA; }

    public double[] getVectorB() { return VectorB; }
    public void setVectorB(double[] vectorB) { VectorB = vectorB; }

    public double[] getVectorX() { return vectorX; }
    public void setVectorX(double[] vectorX) { this.vectorX = vectorX; }

    public boolean isValido() { return valido; }
    public void setValido(boolean valido) { this.valido = valido; }

    @Override
    public String toString() {
        return "GaussJordan{" +
                "MN=" + MN +
                ", MatrizA=" + Arrays.toString(MatrizA) +
                ", VectorB=" + Arrays.toString(VectorB) +
                ", vectorX=" + Arrays.toString(vectorX) +
                ", valido=" + valido +
                '}';
    }
}
