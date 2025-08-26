package mx.ude.itses.JMTG.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import mx.ude.itses.JMTG.MetodosNumericos.domain.DDNewton;
import mx.ude.itses.JMTG.MetodosNumericos.domain.Lagrange;
import mx.ude.itses.JMTG.MetodosNumericos.services.UnidadIVService;


@Controller
@Slf4j
public class Unit4Controller {

    @Autowired
    private UnidadIVService unidadIVService;

    @GetMapping("/unit4")
    public String index(Model model) {
        return "unit4/index";
    }

    // Métodos para Diferencias Divididas de Newton
    @GetMapping("unit4/formddnewton")
    public String formDDNewton(Model model) {
        DDNewton modelNewton = new DDNewton();
        model.addAttribute("modelNewton", modelNewton);
        return "unit4/ddnewton/formddnewton";
    }

    @PostMapping("unit4/solveddnewton")
    public String solveDDNewton(DDNewton modelNewton, Model model) {
        DDNewton result = unidadIVService.AlgoritmoDDNewton(modelNewton);
        model.addAttribute("resultNewton", result);
        return "unit4/ddnewton/solveddnewton";
    }

    // Métodos para Lagrange
    @GetMapping("unit4/formlagrange")
    public String formLagrange(Model model) {
        Lagrange modelLagrange = new Lagrange();
        model.addAttribute("modelLagrange", modelLagrange);
        return "unit4/lagrange/formlagrange";
    }

    @PostMapping("unit4/solvelagrange")
  public String solveLagrange(Lagrange modelLagrange, Model model) {
    Lagrange result = unidadIVService.AlgoritmoLagrange(modelLagrange);
    model.addAttribute("resultLagrange", result);

        return "unit4/lagrange/solvelagrange";
    }
}
