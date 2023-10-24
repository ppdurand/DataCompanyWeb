package com.example.DataCompanyWeb.controllers;

import com.example.DataCompanyWeb.DTO.EditCollaboratorDTO;
import com.example.DataCompanyWeb.DTO.NewCollaboratorDTO;
import com.example.DataCompanyWeb.enums.CollaboratorFunction;
import com.example.DataCompanyWeb.enums.CollaboratorType;
import com.example.DataCompanyWeb.models.Collaborator;
import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.repository.CollaboratorRepository;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.hibernate.query.sql.spi.ParameterOccurrence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Controller
@RequestMapping("collaborators")
public class CollaboratorController {
    @Autowired
    private CollaboratorRepository collaboratorRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @GetMapping
    public ModelAndView index(){
        List<Collaborator> collaborators = this.collaboratorRepository.findAll();
        ModelAndView mv = new ModelAndView("collaborators/index");
        mv.addObject("collaborators", collaborators);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newCollaborator(NewCollaboratorDTO newCollaborator){
        ModelAndView mv = new ModelAndView("collaborators/new");

        mv.addObject("collaboratorType", CollaboratorType.values());
        mv.addObject("collaboratorFunction", CollaboratorFunction.values());
        mv.addObject("projects", projectRepository.findAll());
        return mv;
    }

    @PostMapping()
    public ModelAndView CreateCollaborator(@Valid NewCollaboratorDTO newCollaborator, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("collaborators/new");
            mv.addObject("collaboratorType", CollaboratorType.values());
            mv.addObject("collaboratorFunction", CollaboratorFunction.values());
            mv.addObject("projects", projectRepository.findAll());
            return mv;
        }
        Collaborator collaborator = newCollaborator.toCollaborator();
        collaboratorRepository.save(collaborator);
        return new ModelAndView("redirect:/collaborators");
    }

    @GetMapping("/{id}")
    public ModelAndView ShowDetails(@PathVariable Long id){
        Optional<Collaborator> optional = this.collaboratorRepository.findById(id);
        if(optional.isPresent()){
            Collaborator collaborator = optional.get();
            ModelAndView mv = new ModelAndView("collaborators/show");
            mv.addObject("collaborator", collaborator);
            return mv;
        }
        return new ModelAndView("redirect:/collaborators");
    }
    @GetMapping("/{id}/edit")
    public ModelAndView EditCollaborator(@PathVariable Long id, EditCollaboratorDTO editCollaborator){

        Optional<Collaborator> optional = this.collaboratorRepository.findById(id);
        if(optional.isPresent()){
            Collaborator collaborator = optional.get();
            editCollaborator.fromCollaborator(collaborator);

            ModelAndView mv = new ModelAndView("collaborators/edit");
            mv.addObject("editCollaboratorDTO", editCollaborator);
            mv.addObject("collaboratorType", CollaboratorType.values());
            mv.addObject("collaboratorFunction", CollaboratorFunction.values());
            mv.addObject("projects", projectRepository.findAll());
            return mv;
        }
        else {
            return new ModelAndView("redirect:/collaborators");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView UpdateCollaborator(@PathVariable Long id,@Valid EditCollaboratorDTO editCollaborator,
                                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("collaborators/new");

            mv.addObject("collaboratorType", CollaboratorType.values());
            mv.addObject("collaboratorFunction", CollaboratorFunction.values());
            mv.addObject("projects", projectRepository.findAll());
            return mv;
        }
        else{
            Optional<Collaborator> optional = this.collaboratorRepository.findById(id);
            if(optional.isPresent()){
                Collaborator collaborator = editCollaborator.toCollaborator(optional.get());
                this.collaboratorRepository.save(collaborator);
                return new ModelAndView("redirect:/collaborators");
            }
            else{
                return new ModelAndView("redirect:/collaborators");
            }
        }
    }

    @GetMapping("{id}/delete")
    public ModelAndView DeleteCollaborator(@PathVariable Long id){
        this.collaboratorRepository.deleteById(id);
        return new ModelAndView("redirect:/collaborators");
    }
}
