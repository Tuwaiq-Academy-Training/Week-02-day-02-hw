package com.example.homework6.Model;

import java.time.LocalDate;

public class Task {

    public enum statusE{ done, notDone }

    private String id;
    private String title;
    private String description;
    private statusE status;
    private LocalDate deadline;
    private Person person;


    public Task(String id, String title, String description, statusE status, LocalDate deadline, Person person) {
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

    public statusE getStatus() {
        return status;
    }

    public void setStatus(statusE status) {
        this.status = status;
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
