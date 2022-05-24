package com.example.h02w02.models;

import java.time.LocalDate;
import java.util.Date;

public class Task {
    private String ID, desc, title;
    private boolean done;
    private LocalDate deadline;
    private Person person;

    public Task(String ID, String desc, String title, boolean done, LocalDate deadline, Person person) {
        this.ID = ID;
        this.desc = desc;
        this.title = title;
        this.done = done;
        this.deadline = deadline;
        this.person = person;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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
