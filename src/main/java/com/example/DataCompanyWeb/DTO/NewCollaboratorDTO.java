package com.example.DataCompanyWeb.DTO;

import com.example.DataCompanyWeb.enums.CollaboratorFunction;
import com.example.DataCompanyWeb.enums.CollaboratorType;
import com.example.DataCompanyWeb.models.Collaborator;
import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

public class NewCollaboratorDTO {
    private ProjectRepository projectRepository;

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

    public Project getProjectId() {
        return project;
    }

    public void setProjectId(Project project) {
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

    public Collaborator toCollaborator() {
        Collaborator collaborator = new Collaborator(this.name, this.lastName, this.project,
                this.collaboratorType, this.collaboratorFunction, this.salary);
        return collaborator;
    }
}
