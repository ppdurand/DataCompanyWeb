package com.example.DataCompanyWeb.controllers;

import com.example.DataCompanyWeb.DTO.NewCollaboratorDTO;
import com.example.DataCompanyWeb.enums.CollaboratorFunction;
import com.example.DataCompanyWeb.enums.CollaboratorType;
import com.example.DataCompanyWeb.models.Collaborator;
import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.repository.CollaboratorRepository;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import org.hibernate.query.sql.spi.ParameterOccurrence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("collaborators")
public class CollaboratorController {
    @Autowired
    private CollaboratorRepository collaboratorRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @GetMapping
    public ModelAndView index(){
        Optional<List<Collaborator>> optional = Optional.ofNullable(this.collaboratorRepository.findAll());
        if(optional.isEmpty()){
            return new ModelAndView("redirect:/collaborators/new");
        }
        ModelAndView mv = new ModelAndView("collaborators/index");
        mv.addObject("collaborator", optional);
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

}
