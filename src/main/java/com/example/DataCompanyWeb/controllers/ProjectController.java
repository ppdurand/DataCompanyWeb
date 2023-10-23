package com.example.DataCompanyWeb.controllers;


import com.example.DataCompanyWeb.DTO.EditProjectDTO;
import com.example.DataCompanyWeb.DTO.NewProjectDTO;
import com.example.DataCompanyWeb.enums.ProjectStatus;
import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.enums.ProjectArea;
import com.example.DataCompanyWeb.repository.ProjectRepository;
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
    private ProjectRepository projectRepository;

    @GetMapping
    public ModelAndView index(){
        List<Project> projects = this.projectRepository.findAll();
        ModelAndView mv = new ModelAndView("projects/index");
        mv.addObject("projects", projects);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newProject(NewProjectDTO newProject){
        ModelAndView mv = new ModelAndView("projects/new");
        mv.addObject("projectArea", ProjectArea.values());
        return mv;
    }

    @PostMapping()
    public ModelAndView CreateProject(@Valid NewProjectDTO newProject, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("projects/new");
            mv.addObject("projectArea", ProjectArea.values());
            return mv;
        }

        Project project = newProject.toProject();
        this.projectRepository.save(project);
        return new ModelAndView("redirect:/projects");
    }

    @GetMapping("/{id}")
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

    @GetMapping("/{id}/delete")
    public String Delete(@PathVariable  Long id){
        this.projectRepository.deleteById(id);
        return "redirect:/projects";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView EditProject(@PathVariable Long id, EditProjectDTO editProjectDTO){
        Optional<Project> optional = this.projectRepository.findById(id);
        if(optional.isPresent()){
            Project project = optional.get();
            editProjectDTO.fromProject(project);

            ModelAndView mv = new ModelAndView("projects/edit");
            mv.addObject("editProjectDTO", editProjectDTO);
            mv.addObject("projectArea", ProjectArea.values());
            mv.addObject("projectStatus", ProjectStatus.values());
            return mv;
        }
        else {
            ModelAndView mv = new ModelAndView("redirect:/projects");
            return mv;
        }
    }

    @PostMapping("/{id}")
    public ModelAndView Update(@PathVariable Long id, @Valid EditProjectDTO editProject, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("projects/new");
            mv.addObject("projectArea", ProjectArea.values());
            return mv;
        }
        else{
            Optional<Project> optional = this.projectRepository.findById(id);

            if(optional.isPresent()){
                Project project = editProject.toProject(optional.get());
                this.projectRepository.save(project);
                return new ModelAndView("redirect:/projects");
            }
            else {
                return new ModelAndView("redirect:/projects");
            }
        }
    }
}
