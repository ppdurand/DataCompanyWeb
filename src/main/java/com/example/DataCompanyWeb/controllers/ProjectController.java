package com.example.DataCompanyWeb.controllers;


import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.models.ProjectArea;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    public ModelAndView getProject(){
        List<Project> projects = this.projectRepository.findAll();
        ModelAndView mv = new ModelAndView("projects/index");
        mv.addObject("projects", projects);
        return mv;
    }

    @GetMapping("/projects/new")
    public ModelAndView newProject(){
        ModelAndView mv = new ModelAndView("projects/newProject");
        mv.addObject("projectArea", ProjectArea.values());
        return mv;
    }

    @PostMapping("/projects")
    public String CreateProject(Project project){
        
        return "redirect:/projects";
    }
}
