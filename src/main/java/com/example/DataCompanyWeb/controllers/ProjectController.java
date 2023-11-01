package com.example.DataCompanyWeb.controllers;


import com.example.DataCompanyWeb.DTO.EditProjectDTO;
import com.example.DataCompanyWeb.DTO.NewProjectDTO;
import com.example.DataCompanyWeb.enums.ProjectStatus;
import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.enums.ProjectArea;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import com.example.DataCompanyWeb.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public ModelAndView index(){
        return this.projectService.GetAll();
    }

    @GetMapping("/new")
    public ModelAndView newProject(){
        return this.projectService.CreateProject();
    }

    @PostMapping()
    public ModelAndView CreateProject(@Valid NewProjectDTO newProject, BindingResult bindingResult){
        return this.projectService.Post(newProject, bindingResult);
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id){
        return this.projectService.GetById(id);
    }

    @GetMapping("/{id}/delete")
    public ModelAndView Delete(@PathVariable  Long id){
        return this.projectService.Delete(id);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView EditProject(@PathVariable Long id, EditProjectDTO editProjectDTO){
        return this.projectService.EditProject(id, editProjectDTO);
    }

    @PostMapping("/{id}")
    public ModelAndView Update(@PathVariable Long id, @Valid EditProjectDTO editProject, BindingResult bindingResult){
        return this.projectService.Update(id, editProject, bindingResult);
    }
}
