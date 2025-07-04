package mx.ude.itses.JMTG.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Biseccion;
import mx.ude.itses.JMTG.MetodosNumericos.domain.NewtonRaphson;
import mx.ude.itses.JMTG.MetodosNumericos.domain.PuntoFijo;
import mx.ude.itses.JMTG.MetodosNumericos.domain.ReglaFalsa;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Secante;
import mx.ude.itses.JMTG.MetodosNumericos.domain.SecanteModificado;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIServiceImpl implements UnidadIIService {

    @Override
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion) {
        ArrayList<Biseccion> respuesta = new ArrayList<>();
        double XL, XU, XRa, XRn, FXL, FXU, FXR, Ea;

        XL = biseccion.getXL();
        XU = biseccion.getXU();
        XRa = 0;
        Ea = 100;
        // Verificamos que en el intervalo definido haya un cambio de signo
        FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
        FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
        if (FXL * FXU < 0) {
            for (int i = 1; i <= biseccion.getIteracionesMaximas(); i++) {
                XRn = (XL + XU) / 2;
                FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
                FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
                FXR = Funciones.Ecuacion(biseccion.getFX(), XRn);
                if (i != 1) {
                    Ea = Funciones.ErrorRelativo(XRn, XRa);
                }
                Biseccion renglon = new Biseccion();
                renglon.setXL(XL);
                renglon.setXU(XU);
                renglon.setXR(XRn);
                renglon.setFXL(FXL);
                renglon.setFXU(FXU);
                renglon.setFXR(FXR);
                renglon.setEa(Ea);
                if (FXL * FXR < 0) {
                    XU = XRn;
                } else if (FXL * FXR > 0) {
                    XL = XRn;
                } else if (FXL * FXR == 0) {
                    break;
                }
                XRa = XRn;
                respuesta.add(renglon);
                if (Ea <= biseccion.getEa()) {
                    break;
                }
            }
        } else {
            Biseccion renglon = new Biseccion();
            // renglon.setIntervaloInvalido(true);
            respuesta.add(renglon);
        }

        return respuesta;
    }

    @Override
    public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa datos) {
        ArrayList<ReglaFalsa> resultado = new ArrayList<>();
        double XL = datos.getXL();
        double XU = datos.getXU();
        double XRn, XRa = 0;
        double Ea = 100;

        for (int i = 1; i <= datos.getIteracionesMaximas(); i++) {
            double FXL = Funciones.Ecuacion(datos.getFX(), XL);
            double FXU = Funciones.Ecuacion(datos.getFX(), XU);
            XRn = XU - (FXU * (XL - XU)) / (FXL - FXU);
            double FXR = Funciones.Ecuacion(datos.getFX(), XRn);

            if (i != 1) {
                Ea = Funciones.ErrorRelativo(XRn, XRa);
            }

            ReglaFalsa fila = new ReglaFalsa();
            fila.setXL(XL);
            fila.setXU(XU);
            fila.setXR(XRn);
            fila.setFXL(FXL);
            fila.setFXU(FXU);
            fila.setFXR(FXR);
            fila.setEa(Ea);
            resultado.add(fila);

            if (FXL * FXR < 0) {
                XU = XRn;
            } else {
                XL = XRn;
            }

            XRa = XRn;

            if (Ea <= datos.getEaPermitido()) {
                break;
            }
        }

        return resultado;
    }

    @Override
    public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo datos) {
        ArrayList<PuntoFijo> resultado = new ArrayList<>();
        double Xi = datos.getXi();
        double Xn, Ea = 100;

        for (int i = 1; i <= datos.getIteracionesMaximas(); i++) {
            Xn = Funciones.Ecuacion(datos.getGX(), Xi);
            Ea = i == 1 ? 100 : Funciones.ErrorRelativo(Xn, Xi);

            PuntoFijo fila = new PuntoFijo();
            fila.setXi(Xi);
            fila.setXn(Xn);
            fila.setGXi(Funciones.Ecuacion(datos.getGX(), Xi));
            fila.setEa(Ea);
            resultado.add(fila);

            Xi = Xn;

            if (Ea <= datos.getEaPermitido()) {
                break;
            }
        }

        return resultado;
    }

    @Override
    public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphson(NewtonRaphson datos) {
        ArrayList<NewtonRaphson> resultado = new ArrayList<>();
        double Xi = datos.getXi();
        double Xn = Xi;
        double Ea = 100;

        for (int i = 1; i <= datos.getIteracionesMaximas(); i++) {
            double Fxi = Funciones.Ecuacion(datos.getFX(), Xi);
            double Fdxi = Funciones.Ecuacion(datos.getDFX(), Xi);

            if (Fdxi == 0) {
                // Derivada cero, no se puede dividir
                break;
            }

            Xn = Xi - (Fxi / Fdxi);
            Ea = i == 1 ? 100 : Funciones.ErrorRelativo(Xn, Xi);

            NewtonRaphson fila = new NewtonRaphson();
            fila.setXi(Xi);
            fila.setXn(Xn);
            fila.setFxi(Fxi);
            fila.setFdxi(Fdxi);
            fila.setEa(Ea);
            resultado.add(fila);

            Xi = Xn;

            if (Ea <= datos.getEaPermitido()) {
                break;
            }
        }

        return resultado;
    }
    
    @Override
public ArrayList<Secante> AlgoritmoSecante(Secante datos) {
    ArrayList<Secante> resultado = new ArrayList<>();
    double Xi_1 = datos.getXi_1();
    double Xi = datos.getXi();
    double Xn = Xi;
    double Ea = 100;

    for (int i = 1; i <= datos.getIteracionesMaximas(); i++) {
        double Fxi_1 = Funciones.Ecuacion(datos.getFX(), Xi_1);
        double Fxi = Funciones.Ecuacion(datos.getFX(), Xi);

        if ((Fxi - Fxi_1) == 0) break; // evita división por cero

        Xn = Xi - (Fxi * (Xi_1 - Xi)) / (Fxi_1 - Fxi);

        Ea = i == 1 ? 100 : Funciones.ErrorRelativo(Xn, Xi);

        Secante fila = new Secante();
        fila.setXi_1(Xi_1);
        fila.setXi(Xi);
        fila.setXn(Xn);
        fila.setFxi_1(Fxi_1);
        fila.setFxi(Fxi);
        fila.setEa(Ea);
        resultado.add(fila);

        if (Ea <= datos.getEaPermitido()) break;

        Xi_1 = Xi;
        Xi = Xn;
    }

    return resultado;
}

@Override
public ArrayList<SecanteModificado> AlgoritmoSecanteModificado(SecanteModificado datos) {
    ArrayList<SecanteModificado> resultado = new ArrayList<>();
    double Xi = datos.getXi();
    double Xn = Xi;
    double Ea = 100;

    double h = 1e-5; // incremento pequeño

    for (int i = 1; i <= datos.getIteracionesMaximas(); i++) {
        double Fxi = Funciones.Ecuacion(datos.getFX(), Xi);
        double dFXi = (Funciones.Ecuacion(datos.getFX(), Xi + h) - Fxi) / h;

        if (dFXi == 0) break;

        Xn = Xi - Fxi / dFXi;
        Ea = i == 1 ? 100 : Funciones.ErrorRelativo(Xn, Xi);

        SecanteModificado fila = new SecanteModificado();
        fila.setXi(Xi);
        fila.setXn(Xn);
        fila.setFxi(Fxi);
        fila.setDFXi(dFXi);
        fila.setEa(Ea);
        resultado.add(fila);

        if (Ea <= datos.getEaPermitido()) break;

        Xi = Xn;
    }

    return resultado;
}


}
