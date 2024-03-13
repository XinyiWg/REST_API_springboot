package com.example.studentManagement.service;

import com.example.studentManagement.dao.StudentDao;
import com.example.studentManagement.dao.UniversityProgramDao;
import com.example.studentManagement.exceptions.InvalidProgramException;
import com.example.studentManagement.exceptions.StudentEmptyNameException;
import com.example.studentManagement.exceptions.StudentNotExist;
import com.example.studentManagement.mapper.StudentMapper;
import com.example.studentManagement.model.Student;
import com.example.studentManagement.model.UniversityProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    private StudentDao studentDao;
    private UniversityProgramDao universityProgramDao;
    private StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentDao studentDao, UniversityProgramDao universityProgramDao, StudentMapper studentMapper) {
        this.studentDao = studentDao;
        this.universityProgramDao = universityProgramDao;
        this.studentMapper = studentMapper;
    }






    public Student addStudent(Student student){
        if(student.getName().isEmpty()){
            throw new StudentEmptyNameException("student name shouldn't be empty");
        }
        return studentDao.save(student);
    }

    public Student updateStudent(Student student){
        if (student.getId()==-1||studentDao.existsById(student.getId())){
            throw new StudentNotExist("student does not exist");
        }
        return studentDao.save(student);
    }
    public List<Student> getAllStudents(){
        return (List<Student>) studentDao.findAll();
    }

    public Optional<Student> getStudentById(Long id){
        return (Optional<Student>) studentDao.findById(id);
    }

    public Student assignProgram(Long studentId, Long programId){
        if (!studentDao.existsById(studentId)){
            throw new StudentNotExist("student doesn't exist");
        }
        if(!universityProgramDao.existsById(programId)){
            throw new InvalidProgramException("program doesn't exist");
        }
        Student s=getStudentById(studentId).get();
        UniversityProgram p=universityProgramDao.findById(programId).get();
        s.setUniversityProgram(p);
        return studentDao.save(s);


    }
    public List<Student> getStudentsByName(String name){
        return studentDao.findByName(name);

    }
//
    public List<Student> getStudentNameWithChar(String name){
        return studentMapper.getStudentsWithChar("%"+name+"%");
    }


    public List<Student> getStudentsByProgram(String p, Integer year){
        return studentMapper.getStudentsInProgram(p,year);
    }
}
