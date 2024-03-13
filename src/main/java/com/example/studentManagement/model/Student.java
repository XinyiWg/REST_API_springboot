package com.example.studentManagement.model;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="university_program_id")
    private UniversityProgram universityProgram;
    @Column(nullable = false,name="name")
    private String name;
    public Student() {
    }

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }



    public Long getId() {
        return id!=null?id:-1L;
    }

    public String getName() {
        return name;
    }

    public UniversityProgram getUniversityProgram() {
        return universityProgram;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniversityProgram(UniversityProgram universityProgram) {
        this.universityProgram = universityProgram;
    }

    @Override
    public String toString() {
        String str="";
        str+="Primary id: " + getId();
        str+=" Name: " + getName();
        return str;

    }
}
