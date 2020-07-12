package br.univille.dacs2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}