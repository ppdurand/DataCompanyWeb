package com.example.DataCompanyWeb.controllers;


import com.example.DataCompanyWeb.DTO.NewProjectDTO;
import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.models.ProjectArea;
import com.example.DataCompanyWeb.models.ProjectStatus;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    public ModelAndView index(){
        List<Project> projects = this.projectRepository.findAll();
        ModelAndView mv = new ModelAndView("projects/index");
        mv.addObject("projects", projects);
        return mv;
    }

    @GetMapping("/projects/new")
    public ModelAndView newProject(NewProjectDTO newProject){
        ModelAndView mv = new ModelAndView("projects/new");
        mv.addObject("projectArea", ProjectArea.values());
        return mv;
    }

    @PostMapping("/projects")
    public ModelAndView CreateProject(@Valid NewProjectDTO newProject, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("projects/new");
            mv.addObject("projectArea", ProjectArea.values());
            return mv;
        }

        Project project = newProject.toProject();
        project.setProjectStatus(ProjectStatus.EM_ANDAMENTO);
        this.projectRepository.save(project);
        return new ModelAndView("redirect:/projects");
    }

    @GetMapping("projects/{id}")
    public ModelAndView show(@PathVariable Long id){
        Optional<Project> optional = this.projectRepository.findById(id);
        if(optional.isPresent()){
            Project project = optional.get();
            ModelAndView mv = new ModelAndView("/projects/show");
            mv.addObject("project", project);
            return mv;
        }
        else {
            ModelAndView mv = new ModelAndView("redirect:/projects");
            return mv;
        }
    }
}
