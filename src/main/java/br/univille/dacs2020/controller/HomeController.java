package br.univille.dacs2020.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
   
    @GetMapping("")
    public ModelAndView index() {
        Date agora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY - HH:mm");
        String data = sdf.format(agora);
        return new ModelAndView("home/index", "dataatualizada", data);
    }
}
