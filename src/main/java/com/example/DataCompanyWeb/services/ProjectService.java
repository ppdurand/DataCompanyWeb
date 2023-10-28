package com.example.DataCompanyWeb.services;

import com.example.DataCompanyWeb.DTO.EditProjectDTO;
import com.example.DataCompanyWeb.DTO.NewCollaboratorDTO;
import com.example.DataCompanyWeb.DTO.NewProjectDTO;
import com.example.DataCompanyWeb.enums.ProjectArea;
import com.example.DataCompanyWeb.enums.ProjectStatus;
import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    public ModelAndView GetAll(){
        ModelAndView mv = new ModelAndView("projects/index");
        mv.addObject("projects", this.projectRepository.findAll());
        return mv;
    }

    public ModelAndView GetById(Long id){
        Optional<Project> optional = this.projectRepository.findById(id);
        if(optional.isPresent()){
            ModelAndView mv = new ModelAndView("projects/show");
            mv.addObject("project", optional.get());
            return mv;
        }
        else{
            return new ModelAndView("redirect:/projects");
        }
    }

    public ModelAndView CreateProject(){
        ModelAndView mv = new ModelAndView("projects/new");
        mv.addObject("projectArea", ProjectArea.values());
        return mv;
    }

    public ModelAndView PostProject(@Valid NewProjectDTO newProject, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("redirect:/projects/new");
            mv.addObject("projectArea", ProjectArea.values());
            return mv;
        }
        this.projectRepository.save(newProject.toProject());
        return new ModelAndView("redirect:/projects");
    }

    public ModelAndView DeleteProject(Long id){
        Optional<Project> optional = this.projectRepository.findById(id);
        if(optional.isPresent()){
            this.projectRepository.deleteById(id);
            return new ModelAndView("redirect:/projects");
        }
        else{
            return new ModelAndView("redirect:/projects");
        }
    }

    public ModelAndView EditProject(Long id, EditProjectDTO editProject){
        Optional<Project> optional = this.projectRepository.findById(id);
        if(optional.isPresent()){
            editProject.fromProject(optional.get());

            ModelAndView mv = new ModelAndView("projects/edit");
            mv.addObject("editProjectDTO", editProject);
            mv.addObject("projectArea", ProjectArea.values());
            mv.addObject("projectStatus", ProjectStatus.values());
            return mv;
        }
        else{
            return new ModelAndView("redirect:/projects");
        }
    }

    public ModelAndView UpdateProject(Long id, @Valid EditProjectDTO editProject, BindingResult bindingResult){
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
