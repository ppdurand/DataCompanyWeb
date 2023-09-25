package com.example.DataCompanyWeb.models;

import jakarta.persistence.*;

import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;
    public Project() {}

    public Project(Long id,String name, String description, ProjectArea projectArea, Date creationDate, ProjectStatus projectStatus) {
        this.Id = id;
        this.name = name;
        this.description = description;
        this.projectArea = projectArea;
        this.creationDate = creationDate;
        this.projectStatus = projectStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
