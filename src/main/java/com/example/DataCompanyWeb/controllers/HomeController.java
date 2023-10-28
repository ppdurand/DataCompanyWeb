package com.example.DataCompanyWeb.controllers;

import com.example.DataCompanyWeb.repository.CollaboratorRepository;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    CollaboratorRepository collaboratorRepository;

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("projects", this.projectRepository.findAll().size());
        mv.addObject("collaborators", this.collaboratorRepository.findAll().size());
        return mv;
    }
}
