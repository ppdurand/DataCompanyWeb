package com.example.DataCompanyWeb.controllers;

import com.example.DataCompanyWeb.DTO.EditCollaboratorDTO;
import com.example.DataCompanyWeb.DTO.NewCollaboratorDTO;
import com.example.DataCompanyWeb.enums.CollaboratorFunction;
import com.example.DataCompanyWeb.enums.CollaboratorType;
import com.example.DataCompanyWeb.models.Collaborator;
import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.repository.CollaboratorRepository;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import com.example.DataCompanyWeb.services.CollaboratorService;
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
    private CollaboratorService collaboratorService;
    @GetMapping
    public ModelAndView index(){
        return this.collaboratorService.GetAll();
    }

    @GetMapping("/new")
    public ModelAndView newCollaborator(NewCollaboratorDTO newCollaborator){
        return this.collaboratorService.CreateCollaborator();
    }

    @PostMapping()
    public ModelAndView CreateCollaborator(NewCollaboratorDTO newCollaborator, BindingResult bindingResult){
        return this.collaboratorService.PostCollaborator(newCollaborator, bindingResult);
    }

    @GetMapping("/{id}")
    public ModelAndView ShowDetails(@PathVariable Long id){
        return this.collaboratorService.GetById(id);
    }
    @GetMapping("/{id}/edit")
    public ModelAndView EditCollaborator(@PathVariable Long id, EditCollaboratorDTO editCollaborator){
        return this.collaboratorService.EditCollaborator(id, editCollaborator);
    }

    @PostMapping("/{id}")
    public ModelAndView UpdateCollaborator(@PathVariable Long id,@Valid EditCollaboratorDTO editCollaborator,
                                           BindingResult bindingResult){
        return this.collaboratorService.UpdateCollaborator(id,editCollaborator, bindingResult);
    }

    @GetMapping("{id}/delete")
    public ModelAndView DeleteCollaborator(@PathVariable Long id){
        return this.collaboratorService.DeleteCollaborator(id);
    }
}
