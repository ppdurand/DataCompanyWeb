package com.example.DataCompanyWeb.DTO;

import com.example.DataCompanyWeb.models.Project;
import com.example.DataCompanyWeb.models.ProjectArea;

public class NewProjectDTO {
    private String name;
    private String description;
    private ProjectArea projectArea;

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

    public Project toProject(){
        Project project = new Project(this.name, this.description, this.projectArea);

        project.setName(this.name);
        project.setDescription(this.description);
        project.setProjectArea(this.projectArea);

        return project;
    }
}
