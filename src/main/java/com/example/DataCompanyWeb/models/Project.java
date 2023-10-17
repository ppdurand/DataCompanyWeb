package com.example.DataCompanyWeb.models;

import com.example.DataCompanyWeb.enums.ProjectArea;
import com.example.DataCompanyWeb.enums.ProjectStatus;
import jakarta.persistence.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    private ProjectArea projectArea;
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;
    public Project() {}

    public Project(String name, String description, ProjectArea projectArea) {
        this.name = name;
        this.description = description;
        this.projectArea = projectArea;
        this.projectStatus = ProjectStatus.EM_ANDAMENTO;
    }

    public Project(String name, String description, ProjectArea projectArea, ProjectStatus projectStatus) {
        this.name = name;
        this.description = description;
        this.projectArea = projectArea;
        this.projectStatus = projectStatus;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
