package com.example.hw1_02_02.model;

import java.time.LocalDate;

public class Task {

    private String id;
    private String title;
    private String description;
    private Boolean Status;
    private LocalDate deadline;
    private Person person;


    public Task(String id, String title, String description, Boolean status, LocalDate deadline, Person person) {
        this.id = id;
        this.title = title;
        this.description = description;
        Status = status;
        this.deadline = deadline;
        this.person = person;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
