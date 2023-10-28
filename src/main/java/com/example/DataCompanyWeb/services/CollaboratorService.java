package com.example.DataCompanyWeb.services;

import com.example.DataCompanyWeb.DTO.EditCollaboratorDTO;
import com.example.DataCompanyWeb.DTO.NewCollaboratorDTO;
import com.example.DataCompanyWeb.enums.CollaboratorFunction;
import com.example.DataCompanyWeb.enums.CollaboratorType;
import com.example.DataCompanyWeb.models.Collaborator;
import com.example.DataCompanyWeb.repository.CollaboratorRepository;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Service
public class CollaboratorService {
    @Autowired
    private CollaboratorRepository collaboratorRepository;
    @Autowired
    private ProjectRepository projectRepository;
    public ModelAndView GetAll(){
        List<Collaborator> collaborators = this.collaboratorRepository.findAll();
        ModelAndView mv = new ModelAndView("/collaborators/index");
        mv.addObject("collaborators", collaborators);
        return mv;
    }

    public ModelAndView GetById(Long id){
        Optional<Collaborator> optional = this.collaboratorRepository.findById(id);
        if(optional.isPresent()){
            Collaborator collaborator = optional.get();
            ModelAndView mv = new ModelAndView("collaborators/show");
            mv.addObject("collaborator", collaborator);
            return mv;
        }
        else{
            ModelAndView mv = new ModelAndView("collaborators/new");

            mv.addObject("collaboratorType", CollaboratorType.values());
            mv.addObject("collaboratorFunction", CollaboratorFunction.values());
            mv.addObject("projects", this.projectRepository.findAll());

            return mv;
        }
    }

    public ModelAndView CreateCollaborator(){
        ModelAndView mv = new ModelAndView("collaborators/new");

        mv.addObject("collaboratorType", CollaboratorType.values());
        mv.addObject("collaboratorFunction", CollaboratorFunction.values());
        mv.addObject("projects", projectRepository.findAll());

        return mv;
    }

    public ModelAndView PostCollaborator(NewCollaboratorDTO newCollaboratorDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("collaborator/new");

            mv.addObject("collaboratorType", CollaboratorType.values());
            mv.addObject("collaboratorFunction", CollaboratorFunction.values());
            mv.addObject("projects", this.projectRepository.findAll());

            return mv;
        }
        else{
            Collaborator collaborator = newCollaboratorDTO.toCollaborator();
            this.collaboratorRepository.save(collaborator);

            return new ModelAndView("redirect:/collaborators");
        }
    }

    public ModelAndView EditCollaborator(Long id, EditCollaboratorDTO editCollaborator){
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

    public ModelAndView UpdateCollaborator(Long id, @Valid EditCollaboratorDTO editCollaborator,
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

    public ModelAndView DeleteCollaborator(Long id){
        Optional<Collaborator> optional = this.collaboratorRepository.findById(id);
        if(optional.isPresent()){
            this.collaboratorRepository.deleteById(id);
            return new ModelAndView("redirect:/collaborators");
        }
        else{
            return new ModelAndView("redirect:/collaborators");
        }
    }
}
