package mx.ude.itses.JMTG.MetodosNumericos.services;

import java.util.ArrayList;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Biseccion;
import mx.ude.itses.JMTG.MetodosNumericos.domain.NewtonRaphson;
import mx.ude.itses.JMTG.MetodosNumericos.domain.PuntoFijo;
import mx.ude.itses.JMTG.MetodosNumericos.domain.ReglaFalsa;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Secante;
import mx.ude.itses.JMTG.MetodosNumericos.domain.SecanteModificado;

public interface UnidadIIService {

    ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);

    ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglaFalsa);

    ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntoFijo);

    ArrayList<NewtonRaphson> AlgoritmoNewtonRaphson(NewtonRaphson newtonRaphson);

    ArrayList<Secante> AlgoritmoSecante(Secante secante);

    ArrayList<SecanteModificado> AlgoritmoSecanteModificado(SecanteModificado secanteModificado);// <-- NUEVO
}
