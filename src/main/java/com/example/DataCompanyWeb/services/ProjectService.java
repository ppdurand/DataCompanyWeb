package com.example.DataCompanyWeb.services;

import com.example.DataCompanyWeb.DTO.EditProjectDTO;
import com.example.DataCompanyWeb.DTO.NewCollaboratorDTO;
import com.example.DataCompanyWeb.DTO.NewProjectDTO;
import com.example.DataCompanyWeb.enums.ProjectArea;
import com.example.DataCompanyWeb.enums.ProjectStatus;
import com.example.DataCompanyWeb.models.Collaborator;
import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.repository.CollaboratorRepository;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import com.example.DataCompanyWeb.services.interfaces.IGenericService;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IGenericService<NewProjectDTO, EditProjectDTO> {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CollaboratorRepository collaboratorRepository;
    @Override
    public ModelAndView GetAll(){
        ModelAndView mv = new ModelAndView("projects/index");
        mv.addObject("projects", this.projectRepository.findAll());
        return mv;
    }

    @Override
    public ModelAndView GetById(Long id){
        Optional<Project> optional = this.projectRepository.findById(id);
        if(optional.isPresent()){
            List<Collaborator> collaborators = this.collaboratorRepository.findAll();
            int qtdCollaborators = 0;

            for(int i = 1; i <= this.collaboratorRepository.findAll().size() - 1; i++){
                Collaborator collaborator = collaborators.get(i);
                if(collaborator.getProject() == optional.get()){
                    qtdCollaborators++;
                }
            }

            ModelAndView mv = new ModelAndView("projects/show");
            mv.addObject("project", optional.get());
            mv.addObject("collaborators", qtdCollaborators);
            return mv;
        }
        else{
            return new ModelAndView("redirect:/projects");
        }
    }

    public ModelAndView CreateProject(){
        ModelAndView mv = new ModelAndView("projects/new");
        mv.addObject("newProjectDTO", new NewProjectDTO());
        mv.addObject("projectArea", ProjectArea.values());
        return mv;
    }

    @Override
    public ModelAndView Post(NewProjectDTO newProject, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("redirect:/projects/new");
            mv.addObject("projectArea", ProjectArea.values());
            return mv;
        }
        this.projectRepository.save(newProject.toProject());
        return new ModelAndView("redirect:/projects");
    }

    @Override
    public ModelAndView Delete(Long id){
        this.projectRepository.deleteById(id);
        return new ModelAndView("redirect:/projects");
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

    @Override
    public ModelAndView Update(Long id, EditProjectDTO editProject, BindingResult bindingResult){
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
