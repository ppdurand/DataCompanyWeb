package com.example.DataCompanyWeb.DTO;

import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.enums.ProjectArea;
import com.example.DataCompanyWeb.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EditProjectDTO {
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String description;
    private ProjectArea projectArea;
    private ProjectStatus projectStatus;

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectArea getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(ProjectArea projectArea) {
        this.projectArea = projectArea;
    }

    public void FromProject(Project project){
        this.name = project.getName();
        this.description = project.getDescription();
        this.projectArea = project.getProjectArea();
        this.projectStatus = project.getProjectStatus();
    }
}
