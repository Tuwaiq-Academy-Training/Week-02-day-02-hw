package com.example.tasks.Model;

public class Person {
    private String ID,name;
    private Integer age;

    public Person(String ID, String name, Integer age) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
