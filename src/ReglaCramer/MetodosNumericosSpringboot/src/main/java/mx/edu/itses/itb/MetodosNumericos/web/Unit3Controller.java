package mx.edu.itses.itb.MetodosNumericos.web;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.itb.MetodosNumericos.domain.ReglaCramer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import mx.edu.itses.itb.MetodosNumericos.services.UnidadIIIService;

@Controller
@Slf4j
public class Unit3Controller {
    /*@Autowired    
    private UnidadIIIService unidadIIIsrv;*/
    
    
    @GetMapping("/unit3")
    public String index(Model model) {
        return "unit3/index";
    }
    
    @GetMapping("/unit3/formcramer")
    public String formCramer(Model model) {
        ReglaCramer modelCramer = new ReglaCramer();
        model.addAttribute("modelCramer",modelCramer);
        return "unit3/cramer/formcramer";
    }
    
    @PostMapping("/unit3/solvecramer")
    public String solveCramer(ReglaCramer modelCramer,Errors errores,Model model) {
        log.info("OBJECTOS:" +  modelCramer);
        /*ArrayList<Double> A = modelCramer.getMatrizA();
        var solveCramer = unidadIIIsrv.AlgoritmoReglaCramer(modelCramer);
        log.info("Determinantes: " + solveCramer.getDeterminantes());
        log.info("Solución: " + solveCramer.getVectorX());
        model.addAttribute("solveCramer",solveCramer);*/
        return "unit3/cramer/formcramer";
    }
}
