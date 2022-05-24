package com.example.restaurantsysproj.models;

public class PersonClass {

    private String ID , name;
    private int age;

    public PersonClass(String ID, String name, int age) {
        this.ID = ID;
        this.name = name;
        this.age = age;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
