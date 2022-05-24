package com.example.HomeWork2;

import java.time.LocalDate;

public class Task {
    private String ID;
    private String title;
    private String description;
    private String status = "notDone";

    private LocalDate deadline;
    private Person  Person ;

    public Task() {
    }

    public Task(String ID, String title, String description, String status, LocalDate deadline, com.example.HomeWork2.Person person) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
        this.Person = person;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public com.example.HomeWork2.Person getPerson() {
        return Person;
    }

    public void setPerson(com.example.HomeWork2.Person person) {
        Person = person;
    }
}
