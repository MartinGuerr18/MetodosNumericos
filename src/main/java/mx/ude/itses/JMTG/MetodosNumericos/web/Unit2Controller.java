package mx.ude.itses.JMTG.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Biseccion;
import mx.ude.itses.JMTG.MetodosNumericos.domain.NewtonRaphson;
import mx.ude.itses.JMTG.MetodosNumericos.domain.PuntoFijo;
import mx.ude.itses.JMTG.MetodosNumericos.domain.ReglaFalsa;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Secante;
import mx.ude.itses.JMTG.MetodosNumericos.domain.SecanteModificado;
import mx.ude.itses.JMTG.MetodosNumericos.services.UnidadIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j //
public class Unit2Controller {

    @Autowired
    private UnidadIIService bisectionservice;
    
    @GetMapping("/unit_2")
    public String index(Model model) {
        return "unit_2/index";
    }

    @GetMapping("unit2/formbisection")
    public String formBisection(Model model) {
        Biseccion bisection = new Biseccion();

        model.addAttribute("bisection", bisection);

        return "unit2/bisection/formbisection";
    }

    @PostMapping("/unit2/solvebisection")
    public String solvebisection(Biseccion bisection, Model model) {

        // double valorFX = Funciones.Ecuacion(biseccion.getFX(), biseccion.get);
        //log.info("Valor de FX: " + valorFX);
        var solveBisection = bisectionservice.AlgoritmoBiseccion(bisection);

        //log.info("Arreglo: " +solvebisection);
        model.addAttribute("solvebisection", solveBisection);

        return "/unit2/bisection/solvebisection";
    }

    @GetMapping("/unit2/formReglaFalsa")
    public String formReglaFalsa(Model model) {
        ReglaFalsa reglaFalsa = new ReglaFalsa();
        model.addAttribute("reglaFalsa", reglaFalsa);
        return "unit2/reglafalsa/formreglafalsa";
    }

    @PostMapping("/unit2/solveReglaFalsa")
    public String solveReglaFalsa(ReglaFalsa reglaFalsa, Model model) {
        var solvereglafalsa = bisectionservice.AlgoritmoReglaFalsa(reglaFalsa);
        model.addAttribute("solvereglafalsa", solvereglafalsa);
        return "unit2/reglafalsa/solvereglafalsa";
    }

    @GetMapping("/unit2/formpuntofijo")
    public String formPuntoFijo(Model model) {
        PuntoFijo puntoFijo = new PuntoFijo();
        model.addAttribute("puntoFijo", puntoFijo);
        return "unit2/puntofijo/formpuntofijo";
    }

    @PostMapping("/unit2/solvepuntofijo")
    public String solvePuntoFijo(PuntoFijo puntoFijo, Model model) {
        var solvepuntofijo = bisectionservice.AlgoritmoPuntoFijo(puntoFijo);
        model.addAttribute("solvepuntofijo", solvepuntofijo);
        return "unit2/puntofijo/solvepuntofijo";
    }
    
    @GetMapping("/unit2/formnewtonraphson")
public String formNewtonRaphson(Model model) {
    NewtonRaphson newtonRaphson = new NewtonRaphson();
    model.addAttribute("newtonRaphson", newtonRaphson);
    return "unit2/newtonraphson/formnewtonraphson";
}

@PostMapping("/unit2/solvenewtonraphson")
public String solveNewtonRaphson(NewtonRaphson newtonRaphson, Model model) {
    var solvenewtonraphson = bisectionservice.AlgoritmoNewtonRaphson(newtonRaphson);
    model.addAttribute("solvenewtonraphson", solvenewtonraphson);
    return "unit2/newtonraphson/solvenewtonraphson";
}

@GetMapping("/unit2/formsecante")
public String formSecante(Model model) {
    Secante secante = new Secante();
    model.addAttribute("secante", secante);
    return "unit2/secante/formsecante";
}

@PostMapping("/unit2/solvesecante")
public String solveSecante(Secante secante, Model model) {
    var solvesecante = bisectionservice.AlgoritmoSecante(secante);
    model.addAttribute("solvesecante", solvesecante);
    return "unit2/secante/solvesecante";
}

@GetMapping("/unit2/formsecantemodificado")
public String formSecanteModificado(Model model) {
    SecanteModificado secanteModificado = new SecanteModificado();
    model.addAttribute("secanteModificado", secanteModificado);
    return "unit2/secantemodificado/formsecantemodificado";
}

@PostMapping("/unit2/solvesecantemodificado")
public String solveSecanteModificado(SecanteModificado secanteModificado, Model model) {
    var solvesecantemodificado = bisectionservice.AlgoritmoSecanteModificado(secanteModificado);
    model.addAttribute("solvesecantemodificado", solvesecantemodificado);
    return "unit2/secantemodificado/solvesecantemodificado";
}


}
