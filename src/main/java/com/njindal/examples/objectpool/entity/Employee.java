package com.njindal.examples.objectpool.entity;

import java.util.Objects;
import java.util.UUID;

public class Employee {
    private UUID id;
    private String name;
    private String dept;

    private Employee() {
        this.id=UUID.randomUUID();
    }

    private Employee(String name, String dept) {
        this.id=UUID.randomUUID();
        this.name = name;
        this.dept = dept;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(dept, employee.dept);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, dept);
    }

    public static Employee instance(){
        return new Employee();
    }

    public static Employee instance(String name, String dept){
        return new Employee(name,dept);
    }
}
