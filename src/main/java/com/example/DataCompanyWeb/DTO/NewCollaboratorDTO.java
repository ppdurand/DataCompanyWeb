package com.example.DataCompanyWeb.DTO;

import com.example.DataCompanyWeb.enums.CollaboratorFunction;
import com.example.DataCompanyWeb.enums.CollaboratorType;
import com.example.DataCompanyWeb.models.Collaborator;
import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.repository.ProjectRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

public class NewCollaboratorDTO {
    private ProjectRepository projectRepository;

    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String lastName;
    @NotBlank
    @NotNull
    private Timestamp birthdayDate;
    @NotBlank
    @NotNull
    private Long projectId;
    private CollaboratorType collaboratorType;
    private CollaboratorFunction collaboratorFunction;
    @NotBlank
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

    public Timestamp getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Timestamp birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
        Optional<Project> optional = projectRepository.findById(this.projectId);
        Project project = null;
        if (optional.isPresent()) {
            project = optional.get();
        }

        Collaborator collaborator = new Collaborator(this.name, this.lastName, this.birthdayDate, project,
                                                    this.collaboratorType, this.collaboratorFunction, this.salary);
        return collaborator;
    }
}
