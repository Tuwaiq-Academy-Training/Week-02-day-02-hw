package com.example.assignment6.model;

import java.time.LocalDate;

public class Task {

    private String id;
    private String title;
    private String description;
    private String status;
    private LocalDate deadline;
    private Person person;

    public enum taskStatus{
        done, notDone
    }

    public Task(String id, String title, String description, String status, LocalDate deadline, Person person) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
        this.person = person;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Person getPerson() {
        return person;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
