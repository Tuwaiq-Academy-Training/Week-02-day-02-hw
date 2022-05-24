package com.example.atheerhw2;

import java.time.LocalDate;


public class Task {
   private String ID ;
   private String title;
   private String description ;
   private LocalDate deadline ;
   private Person person ;
    private Status status;

    public Task(String ID, String title, String description,
                LocalDate deadline, Person person, Status status) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.person = person;
        this.status = status;
    }

    public enum Status {
        Done,
        notDone;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



}
