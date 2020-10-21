package br.univille.dacs2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.dacs2020.model.Paciente;
import br.univille.dacs2020.service.PacienteService;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ModelAndView index(){
        List<Paciente> listaPacientes = service.getAll();
        return new ModelAndView("paciente/index","listapacientes",listaPacientes);
    }

    @GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Paciente paciente) {
		return new ModelAndView("paciente/form");
    }
    
    @PostMapping(params="form")
    public ModelAndView save(Paciente paciente){
        service.save(paciente);
        return new ModelAndView("redirect:/paciente");
    }

    @GetMapping(value="/alterar/{id}")
	public ModelAndView edit(@PathVariable("id") Paciente paciente) {
		return new ModelAndView("paciente/form","paciente",paciente);
    }
    
    @GetMapping(value="/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Paciente paciente){
        service.delete(paciente);
        return new ModelAndView("redirect:/paciente");
    }
}