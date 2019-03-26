package com.flaviomelo.mvc.web.controller;

import com.flaviomelo.mvc.domain.Cargo;
import com.flaviomelo.mvc.domain.Departamento;
import com.flaviomelo.mvc.service.CargoService;
import com.flaviomelo.mvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private DepartamentoService departamentoService;


    @GetMapping("/cadastrar")
    public String cadastrar( Cargo cargo){
        return "/cargo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("cargos", cargoService.buscarTodos());
        return "/cargo/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Cargo cargo, RedirectAttributes attributes){
        cargoService.salvar(cargo);
        attributes.addFlashAttribute("success","Cargo  criado com sucesso.");
        return "redirect:/cargos/cadastrar";
    }

    @ModelAttribute("departamentos")
    public List<Departamento> listaDepartamentos(){
        return departamentoService.buscarTodos();
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("cargo",cargoService.buscarPorId(id));
        return "/cargo/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Cargo cargo, RedirectAttributes attributes){
        cargoService.editar(cargo);
        attributes.addFlashAttribute("success","Cargo  editado com sucesso.");
        return "redirect:/cargos/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model){
        if(cargoService.cargoTemFuncionarios(id)){
            model.addAttribute("fail","Cargo não removido. Possui funcionário(s) vinculado(s).");
        }else{
            cargoService.excluir(id);
            model.addAttribute("success","Cargo  removido com sucesso.");
        }
        return "redirect:/cargos/listar";
    }
}
