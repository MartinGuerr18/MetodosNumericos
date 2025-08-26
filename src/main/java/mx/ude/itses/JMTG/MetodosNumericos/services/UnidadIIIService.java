package mx.ude.itses.JMTG.MetodosNumericos.services;


import mx.ude.itses.JMTG.MetodosNumericos.domain.Gauss;
import mx.ude.itses.JMTG.MetodosNumericos.domain.GaussJordan;
import mx.ude.itses.JMTG.MetodosNumericos.domain.GaussSeidel;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Jacobi;
import mx.ude.itses.JMTG.MetodosNumericos.domain.ReglaCramer;


public interface UnidadIIIService {


    public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer);
    
    public Gauss AlgoritmoGauss(Gauss modelGauss);
    
    public GaussJordan AlgoritmoGaussJordan(int MN, double[] matrizA, double[] vectorB);
    
    public Jacobi AlgoritmoJacobi(Jacobi jacobi);
    
    public GaussSeidel AlgoritmoGaussSeidel(GaussSeidel seidel);
}
