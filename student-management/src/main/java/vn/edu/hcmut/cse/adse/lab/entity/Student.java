package vn.edu.hcmut.cse.adse.lab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private UUID id;

    private String name;

    private String email;

    private int age;

    public Student() {
    }

    public Student(String name, String email, int age) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
