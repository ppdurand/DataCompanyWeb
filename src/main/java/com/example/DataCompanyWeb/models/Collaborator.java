package com.example.DataCompanyWeb.models;

import com.example.DataCompanyWeb.enums.CollaboratorFunction;
import com.example.DataCompanyWeb.enums.CollaboratorType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Enumerated(EnumType.STRING)
    private CollaboratorType collaboratorType;
    @Enumerated(EnumType.STRING)
    private CollaboratorFunction collaboratorFunction;
    @Column(nullable = false)
    private BigDecimal salary;


    public Collaborator() {}
    public Collaborator(String name, String lastName, Project project, CollaboratorType collaboratorType, CollaboratorFunction collaboratorFunction, BigDecimal salary) {
        this.name = name;
        this.lastName = lastName;
        this.project = project;
        this.collaboratorType = collaboratorType;
        this.collaboratorFunction = collaboratorFunction;
        this.salary = salary;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
