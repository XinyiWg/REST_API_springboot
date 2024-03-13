package com.example.studentManagement.service;

import com.example.studentManagement.dao.UniversityProgramDao;
import com.example.studentManagement.exceptions.InvalidProgramException;
import com.example.studentManagement.exceptions.StudentEmptyNameException;
import com.example.studentManagement.model.Student;
import com.example.studentManagement.model.UniversityProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class UniversityProgramService {
    UniversityProgramDao universityProgramDao;

    @Autowired
    public UniversityProgramService(UniversityProgramDao universityProgramDao) {
        this.universityProgramDao = universityProgramDao;
    }

    public List<UniversityProgram> getAllPrograms(){
        return (List<UniversityProgram>) universityProgramDao.findAll();

    }

    public UniversityProgram addProgram(UniversityProgram universityProgram){
        int currYear= Calendar.getInstance().get(Calendar.YEAR);
        if (universityProgram.getYear()>currYear){
            throw new InvalidProgramException("Year is beyond the current year");

        }
        return universityProgramDao.save(universityProgram);
    }
}
