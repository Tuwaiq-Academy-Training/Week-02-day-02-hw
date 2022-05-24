package com.example.restaurantsysproj.models;

import java.time.LocalDate;

public class TaskClass {
    private String ID ,title ,description ;
    private LocalDate deadline ;
    private PersonClass person;
    private boolean isDone , notDone ;

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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public PersonClass getPerson() {
        return person;
    }

    public void setPerson(PersonClass person) {
        this.person = person;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isNotDone() {
        return notDone;
    }

    public void setNotDone(boolean notDone) {
        this.notDone = notDone;
    }

    public TaskClass(String ID, String title, String description, LocalDate deadline, PersonClass person, boolean isDone, boolean notDone) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.person = person;
        this.isDone = isDone;
        this.notDone = notDone;
    }
}
