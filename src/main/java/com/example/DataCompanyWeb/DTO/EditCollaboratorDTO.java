package com.example.DataCompanyWeb.DTO;

import com.example.DataCompanyWeb.enums.CollaboratorFunction;
import com.example.DataCompanyWeb.enums.CollaboratorType;
import com.example.DataCompanyWeb.models.Collaborator;
import com.example.DataCompanyWeb.models.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class EditCollaboratorDTO {
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String lastName;
    @NotNull
    private Project project;
    private CollaboratorType collaboratorType;
    private CollaboratorFunction collaboratorFunction;
    @NotNull
    private BigDecimal salary;

    public EditCollaboratorDTO(){}

    public EditCollaboratorDTO(String name, String lastName, Project project, CollaboratorType collaboratorType, CollaboratorFunction collaboratorFunction, BigDecimal salary) {
        this.name = name;
        this.lastName = lastName;
        this.project = project;
        this.collaboratorType = collaboratorType;
        this.collaboratorFunction = collaboratorFunction;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public CollaboratorType getCollaboratorType() {
        return collaboratorType;
    }

    public void setCollaboratorType(CollaboratorType collaboratorType) {
        this.collaboratorType = collaboratorType;
    }

    public CollaboratorFunction getCollaboratorFunction() {
        return collaboratorFunction;
    }

    public void setCollaboratorFunction(CollaboratorFunction collaboratorFunction) {
        this.collaboratorFunction = collaboratorFunction;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    //Método pra receber uma Model e transformar em DTO
    public void fromCollaborator(Collaborator collaborator){
        this.name = collaborator.getName();
        this.lastName = collaborator.getLastName();
        this.salary = collaborator.getSalary();
        this.project = collaborator.getProject();
        this.collaboratorType = collaborator.getCollaboratorType();
        this.collaboratorFunction = collaborator.getCollaboratorFunction();
    }
    //Transformar o DTO de volta na Model
    public Collaborator toCollaborator(Collaborator collaborator){
        collaborator.setName(this.getName());
        collaborator.setLastName(this.getLastName());
        collaborator.setProject(this.getProject());
        collaborator.setCollaboratorType(this.getCollaboratorType());
        collaborator.setCollaboratorFunction(this.getCollaboratorFunction());
        collaborator.setSalary(this.getSalary());

        return collaborator;
    }
}
