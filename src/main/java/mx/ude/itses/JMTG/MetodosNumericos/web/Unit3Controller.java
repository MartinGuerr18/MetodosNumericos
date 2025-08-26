package mx.ude.itses.JMTG.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Gauss;
import mx.ude.itses.JMTG.MetodosNumericos.domain.GaussJordan;
import mx.ude.itses.JMTG.MetodosNumericos.domain.GaussSeidel;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Jacobi;
import mx.ude.itses.JMTG.MetodosNumericos.domain.ReglaCramer;
import mx.ude.itses.JMTG.MetodosNumericos.services.UnidadIIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class Unit3Controller {

    @Autowired
    private UnidadIIIService unidadIIIsrv;

    @GetMapping("/unit3")
    public String index(Model model) {
        return "unit3/index";
    }

    @GetMapping("/unit3/formcramer")
    public String formCramer(Model model) {
        ReglaCramer modelCramer = new ReglaCramer();
        model.addAttribute("modelCramer", modelCramer);
        return "unit3/cramer/formcramer";
    }

    @PostMapping("/unit3/solvecramer")
    public String solveCramer(ReglaCramer modelCramer, Errors errores, Model model) {
        log.info("OBJECTOS:" + modelCramer);
        var solveCramer = unidadIIIsrv.AlgoritmoReglaCramer(modelCramer);
        log.info("Solución: " + solveCramer.getVectorX());
        log.info("Determinantes: " + solveCramer.getDeterminantes());
        model.addAttribute("solveCramer", solveCramer);
        return "unit3/cramer/solvecramer";
    }
    
    @GetMapping("/unit3/formgauss")
public String gaussForm(Model model) {
    model.addAttribute("modelGauss", new Gauss());
    return "unit3/gauss/formgauss";
}

@PostMapping("/unit3/solvegauss")
public String solveGauss(Gauss modelGauss, Errors errores, Model model) {
    var solveGauss = unidadIIIsrv.AlgoritmoGauss(modelGauss);
    model.addAttribute("solveGauss", solveGauss);
    return "unit3/gauss/solvegauss";
}

@GetMapping("/unit3/formgaussjordan")
public String formularioGaussJordan(Model model) {
    model.addAttribute("gaussjordan", new GaussJordan());
    return "unit3/gaussjordan/formgaussjordan";
}

@PostMapping("/unit3/solvegaussjordan")
public String resolverGaussJordan(
        @RequestParam int MN,
        @RequestParam("matrizA[]") double[] matrizA,
        @RequestParam("vectorB[]") double[] vectorB,
        Model model) {
    GaussJordan resultado = unidadIIIsrv    .AlgoritmoGaussJordan(MN, matrizA, vectorB);
    model.addAttribute("solveGaussJordan", resultado);
    return "unit3/gaussjordan/solvegaussjordan";
}

@GetMapping("/unit3/formjacobi")
public String formularioJacobi(Model model) {
    model.addAttribute("jacobi", new Jacobi());
    return "unit3/jacobi/formjacobi";
}

@PostMapping("/unit3/solvejacobi")
public String resolverJacobi(Jacobi jacobi, Model model) {
    Jacobi resultado = unidadIIIsrv.AlgoritmoJacobi(jacobi);
    model.addAttribute("solveJacobi", resultado);
    return "unit3/jacobi/solvejacobi";
}

@GetMapping("/unit3/formgaussseidel")
public String formularioSeidel(Model model) {
    model.addAttribute("seidel", new GaussSeidel());
    return "unit3/seidel/formgaussseidel";
}

@PostMapping("/unit3/solvegaussseidel")
public String resolverSeidel(GaussSeidel seidel, Model model) {
    GaussSeidel resultado = unidadIIIsrv.AlgoritmoGaussSeidel(seidel);
    model.addAttribute("solveSeidel", resultado);
    return "unit3/seidel/solvegaussseidel";
}

}
