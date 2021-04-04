package com.egc.actividadpre_parcial;

import java.io.Serializable;

public class Employee implements Serializable {
    String identificationCard;
    String name;
    String surname;
    int age;
    String workPosition;
    String email;
    double salary;

    public Employee(String identificationCard, String name, String surname, int age, String workPosition, String email, double salary) {
        this.identificationCard = identificationCard;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.workPosition = workPosition;
        this.email = email;
        this.salary = salary;
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
