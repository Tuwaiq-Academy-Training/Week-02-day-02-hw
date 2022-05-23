package com.example.javaDay02.model;

public class Person {
    private String ID;
    private String name;
    private int age;

    public Person(String ID, String name, int age) {
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

    @Override
    public String toString() {
        return "Person{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
