package mx.ude.itses.JMTG.MetodosNumericos.services;

import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;

import mx.ude.itses.JMTG.MetodosNumericos.domain.Gauss;
import mx.ude.itses.JMTG.MetodosNumericos.domain.GaussJordan;
import mx.ude.itses.JMTG.MetodosNumericos.domain.GaussSeidel;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Jacobi;
import mx.ude.itses.JMTG.MetodosNumericos.domain.ReglaCramer;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIIServiceImpl implements UnidadIIIService {

    @Override
    public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer) {
        ArrayList<Double> determinantes = new ArrayList<>();
        ArrayList<Double> vectorX = new ArrayList<>(); // Corregido: "vectorX" en lugar de "verctorX"

        int n = modelCramer.getMN();
        ArrayList<Double> A = modelCramer.getMatrizA();
        ArrayList<Double> B = modelCramer.getVectorB();

        // Validación básica
        if (A == null || B == null || A.size() != n * n || B.size() != n) {
            log.error("Datos de entrada inválidos para Cramer");
            return modelCramer;
        }

        // Convertir ArrayList a matriz 2D para facilitar el trabajo
        double[][] matrizA = convertirArrayListAMatriz(A, n);

        // Calcular determinante principal
        double detPrincipal = calcularDeterminante(matrizA, n);
        determinantes.add(detPrincipal);
        log.info("Determinante principal: " + detPrincipal);

        // Verificar si el sistema tiene solución única
        if (Math.abs(detPrincipal) < 1e-10) {
            log.error("El sistema no tiene solución única (determinante = 0)");
            modelCramer.setDeterminantes(determinantes);
            return modelCramer;
        }

        // Calcular determinantes para cada variable Xi
        for (int i = 0; i < n; i++) {
            double[][] matrizXi = crearMatrizParaVariable(matrizA, B, i, n);
            double detXi = calcularDeterminante(matrizXi, n);
            determinantes.add(detXi);
            log.info("Determinante X" + (i + 1) + ": " + detXi);

            // Calcular la variable Xi
            double xi = detXi / detPrincipal;
            vectorX.add(xi);
            log.info("X" + (i + 1) + " = " + xi);
        }

        modelCramer.setVectorX(vectorX);
        modelCramer.setDeterminantes(determinantes);
        return modelCramer;
    }

    /**
     * Convierte ArrayList a matriz 2D
     */

    /**
     * Crea una matriz reemplazando la columna especificada con el vector B
     */
    private double[][] crearMatrizParaVariable(double[][] matrizOriginal, ArrayList<Double> vectorB,
            int columnaAReemplazar, int dimension) {
        double[][] nuevaMatriz = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (j == columnaAReemplazar) {
                    nuevaMatriz[i][j] = vectorB.get(i);
                } else {
                    nuevaMatriz[i][j] = matrizOriginal[i][j];
                }
            }
        }

        return nuevaMatriz;
    }

    /**
     * Calcula el determinante de una matriz n×n usando recursión y expansión
     * por cofactores
     */
    private double calcularDeterminante(double[][] matriz, int dimension) {
        // Caso base: matriz 1×1
        if (dimension == 1) {
            return matriz[0][0];
        }

        // Caso optimizado: matriz 2×2
        if (dimension == 2) {
            return Det2(matriz);
        }

        // Caso optimizado: matriz 3×3
        if (dimension == 3) {
            return Det3(matriz);
        }

        // Caso general: expansión por cofactores (primera fila)
        double determinante = 0;
        for (int j = 0; j < dimension; j++) {
            double cofactor = matriz[0][j] * calcularCofactor(matriz, 0, j, dimension);
            determinante += (j % 2 == 0) ? cofactor : -cofactor;
        }

        return determinante;
    }

    /**
     * Calcula el cofactor de un elemento específico
     */
    private double calcularCofactor(double[][] matriz, int filaExcluir, int columnaExcluir, int dimension) {
        double[][] subMatriz = new double[dimension - 1][dimension - 1];
        int filaSubMatriz = 0;

        for (int i = 0; i < dimension; i++) {
            if (i == filaExcluir) {
                continue;
            }

            int columnaSubMatriz = 0;
            for (int j = 0; j < dimension; j++) {
                if (j == columnaExcluir) {
                    continue;
                }

                subMatriz[filaSubMatriz][columnaSubMatriz] = matriz[i][j];
                columnaSubMatriz++;
            }
            filaSubMatriz++;
        }

        return calcularDeterminante(subMatriz, dimension - 1);
    }

// Métodos optimizados existentes se mantienen para mejor rendimiento
    private double Det2(double[][] A) {
        return A[0][0] * A[1][1] - A[0][1] * A[1][0];
    }

    private double Det3(double[][] A) {
        return A[0][0] * A[1][1] * A[2][2]
                + A[0][1] * A[1][2] * A[2][0]
                + A[0][2] * A[1][0] * A[2][1]
                - A[2][0] * A[1][1] * A[0][2]
                - A[2][1] * A[1][2] * A[0][0]
                - A[2][2] * A[1][0] * A[0][1];
    }

    private double[][] convertirArrayListAMatriz(ArrayList<Double> lista, int n) {
        double[][] matriz = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = lista.get(i * n + j);
            }
        }
        return matriz;
    }

  @Override
    public Gauss AlgoritmoGauss(Gauss modelGauss) {
        int n = modelGauss.getMN();
        ArrayList<Double> Aflat = modelGauss.getMatrizA();
        ArrayList<Double> B = modelGauss.getVectorB();

        // Convertimos a matriz bidimensional
        double[][] A = new double[n][n];
        double[] b = new double[n];
        double[] x = new double[n];

        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = Aflat.get(k++);
            }
            b[i] = B.get(i);
        }

        // Eliminación hacia adelante
        for (int p = 0; p < n; p++) {
            // Pivoteo parcial simple
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }

            // Intercambiar filas
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            // Verificar singularidad
            if (Math.abs(A[p][p]) <= 1e-10) {
                modelGauss.setValido(false);
                return modelGauss;
            }

            // Eliminación
            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // Sustitución hacia atrás
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }

        // Guardar solución en el modelo
        ArrayList<Double> solucion = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            solucion.add(x[i]);
        }
        modelGauss.setVectorX(solucion);
        modelGauss.setValido(true);

        return modelGauss;
    }
    
    @Override
public GaussJordan AlgoritmoGaussJordan(int MN, double[] matrizA, double[] vectorB) {
    GaussJordan resultado = new GaussJordan(MN, matrizA.clone(), vectorB.clone());
    double[][] A = new double[MN][MN];
    double[] B = vectorB.clone();

    // Pasar a matriz 2D
    for (int i = 0; i < MN; i++) {
        for (int j = 0; j < MN; j++) {
            A[i][j] = matrizA[i * MN + j];
        }
    }

    // Gauss-Jordan
    for (int i = 0; i < MN; i++) {
        // Pivote
        double pivote = A[i][i];
        if (Math.abs(pivote) < 1e-10) {
            resultado.setValido(false);
            return resultado;
        }

        // Normalizar fila pivote
        for (int j = 0; j < MN; j++) {
            A[i][j] /= pivote;
        }
        B[i] /= pivote;

        // Eliminar en otras filas
        for (int k = 0; k < MN; k++) {
            if (k != i) {
                double factor = A[k][i];
                for (int j = 0; j < MN; j++) {
                    A[k][j] -= factor * A[i][j];
                }
                B[k] -= factor * B[i];
            }
        }
    }

    // Guardar solución
    resultado.setVectorX(B);
    resultado.setValido(true);
    return resultado;
}

@Override
public Jacobi AlgoritmoJacobi(Jacobi jacobi) {
    if (!jacobi.esValido()) {
        return jacobi;
    }

    int n = jacobi.getMN();
    double[][] A = new double[n][n];
    double[] b = new double[n];
    double[] x = new double[n];
    double[] xNuevo = new double[n];

    // Llenar matriz A
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            A[i][j] = jacobi.getMatrizA().get(i * n + j);
        }
        b[i] = jacobi.getVectorB().get(i);
        x[i] = 0; // inicialización en cero
    }

    int maxIter = jacobi.getMaxIteraciones() > 0 ? jacobi.getMaxIteraciones() : 50;
    double tol = jacobi.getTolerancia() > 0 ? jacobi.getTolerancia() : 1e-6;

    for (int iter = 0; iter < maxIter; iter++) {
        for (int i = 0; i < n; i++) {
            double suma = 0;
            for (int j = 0; j < n; j++) {
                if (j != i) suma += A[i][j] * x[j];
            }
            xNuevo[i] = (b[i] - suma) / A[i][i];
        }

        // Guardar iteración
        ArrayList<Double> paso = new ArrayList<>();
        for (int i = 0; i < n; i++) paso.add(xNuevo[i]);
        jacobi.getIteraciones().add(paso);

        // Verificar convergencia
        double error = 0;
        for (int i = 0; i < n; i++) {
            error += Math.abs(xNuevo[i] - x[i]);
            x[i] = xNuevo[i];
        }
        if (error < tol) break;
    }

    // Guardar solución
    ArrayList<Double> sol = new ArrayList<>();
    for (double val : x) sol.add(val);
    jacobi.setVectorX(sol);

    return jacobi;
}

@Override
public GaussSeidel AlgoritmoGaussSeidel(GaussSeidel seidel) {
    if (!seidel.esValido()) {
        return seidel;
    }

    int n = seidel.getMN();
    double[][] A = new double[n][n];
    double[] b = new double[n];
    double[] x = new double[n];

    // Llenar matriz A
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            A[i][j] = seidel.getMatrizA().get(i * n + j);
        }
        b[i] = seidel.getVectorB().get(i);
        x[i] = 0; // inicialización
    }

    int maxIter = seidel.getMaxIteraciones() > 0 ? seidel.getMaxIteraciones() : 50;
    double tol = seidel.getTolerancia() > 0 ? seidel.getTolerancia() : 1e-6;

    for (int iter = 0; iter < maxIter; iter++) {
        double[] xAnt = x.clone();
        for (int i = 0; i < n; i++) {
            double suma = 0;
            for (int j = 0; j < n; j++) {
                if (j != i) suma += A[i][j] * x[j];
            }
            x[i] = (b[i] - suma) / A[i][i];
        }

        // Guardar iteración
        ArrayList<Double> paso = new ArrayList<>();
        for (int i = 0; i < n; i++) paso.add(x[i]);
        seidel.getIteraciones().add(paso);

        // Verificar convergencia
        double error = 0;
        for (int i = 0; i < n; i++) error += Math.abs(x[i] - xAnt[i]);
        if (error < tol) break;
    }

    // Guardar solución
    ArrayList<Double> sol = new ArrayList<>();
    for (double val : x) sol.add(val);
    seidel.setVectorX(sol);

    return seidel;
}

}
