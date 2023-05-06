package ru.yakovlev.springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //Валидация полей name, surName, department, salary через аннотации
    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty") // Имя не должно быть пустым
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") // имя от 2 до 30 символов
    private String name;
    @Column(name = "surname")
    @NotEmpty(message = "SurName should not be empty") // Фамилия не должна быть пустым
    @Size(min = 2, max = 30, message = "SurName should be between 2 and 30 characters") // Фамилия от 2 до 30 символов
    private String surName;
    @Column(name = "department")
    @NotEmpty(message = "Department should not be empty") // Отдел не должен быть пустым
    @Size(min = 2, max = 15, message = "Department should be between 2 and 15 characters") // Отдел от 2 до 15 символов
    private String department;
    @Column(name = "salary")
    @Min(value = 0, message = "Salary should be greater than 0")
    private int salary;

    public User() {
    }

    public User(String name, String surName, String department, int salary) {
        this.name = name;
        this.surName = surName;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
