package com.example.studentManagement.dao;

import com.example.studentManagement.model.Student;
import com.example.studentManagement.model.UniversityProgram;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UniversityProgramDao extends CrudRepository<UniversityProgram,Long> {


}
