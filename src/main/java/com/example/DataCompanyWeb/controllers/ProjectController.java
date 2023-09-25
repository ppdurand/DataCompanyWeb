package com.example.DataCompanyWeb.controllers;


import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.models.ProjectArea;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class ProjectController {
    @GetMapping("/projects")

    public ModelAndView index(){
        Project nf_Mirror = new Project(
                1L,
                "NF Mirror",
                "Conversação de notas fiscais em uma planilha excel",
                ProjectArea.SEGURANCA,
                new Date(),
                ProjectStatus.EM_ANDAMENTO);


        Project saudePraTodos = new Project(
                2L,
                "Saúde Para Todos",
                "Um projeto que visa a criação de mais orgãos públicos voltados a saúde",
                ProjectArea.SAUDE,
                new Date(),
                ProjectStatus.BLOQUEADO);
        List<Project> projects = Arrays.asList(nf_Mirror, saudePraTodos);

        ModelAndView mv = new ModelAndView("projects/index");
        mv.addObject("projects", projects);

        return mv;
    }
}
