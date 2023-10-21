package com.example.DataCompanyWeb.controllers;

import com.example.DataCompanyWeb.enums.CollaboratorType;
import com.example.DataCompanyWeb.models.Collaborator;
import com.example.DataCompanyWeb.repository.CollaboratorRepository;
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
    public ModelAndView newCollaborator(){
        ModelAndView mv = new ModelAndView("collaborators/new");
        mv.addObject("collaboratorTypes", CollaboratorType.values());
        return mv;
    }

}
