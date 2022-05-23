package com.example.tasks.Model;

import java.time.LocalDate;

public class Task {

    private String id,title,description;
    public enum statusEnum{
        done,
        notDone
    }
    private statusEnum status;
    private LocalDate deadLine;
    private Person person;

    public Task(String id, String title, String description, statusEnum status, LocalDate deadLine, Person person) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadLine = deadLine;
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

    public statusEnum getStatus() {
        return status;
    }

    public void setStatus(statusEnum status) {
        this.status = status;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
