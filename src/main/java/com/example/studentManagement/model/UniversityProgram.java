package com.example.studentManagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="UniversityProgram")
public class UniversityProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    Integer year;
    @Column(nullable = false)
    String program;
    @OneToMany(mappedBy = "universityProgram")
    List<Student> students;
    public UniversityProgram(Long id, Integer year, String program) {
        this.id = id;
        this.year = year;
        this.program=program;
    }

    public UniversityProgram() {
    }

    @Override
    public String toString() {
        return "UniversityProgram{" +
                "id=" + id +
                ", year=" + year +
                ", program='" + program + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public String getProgram() {
        return program;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
