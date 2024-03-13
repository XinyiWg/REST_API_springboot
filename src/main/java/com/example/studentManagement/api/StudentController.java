package com.example.studentManagement.api;


import com.example.studentManagement.exceptions.InvalidProgramException;
import com.example.studentManagement.exceptions.StudentEmptyNameException;
import com.example.studentManagement.exceptions.StudentNotExist;
import com.example.studentManagement.model.Student;
import com.example.studentManagement.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @RequiresPermissions("student:read")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody Student student){
        try{
            Student s =studentService.addStudent(student);
            return ResponseEntity.ok("add ok: "+s.toString());
        }catch (StudentEmptyNameException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @PostMapping(path = "assignProgram/{sid}/{pid}")
    public ResponseEntity<String> assignProgram(@PathVariable("sid") Long stuId, @PathVariable("pid") Long proId){
        try{
            Student s=studentService.assignProgram(stuId,proId);
            return ResponseEntity.ok("assign ok: "+s.toString());

        }catch (StudentNotExist e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }catch (InvalidProgramException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @GetMapping("/name")
    public List<Student> getStudentsByName(@RequestParam String name){
        return studentService.getStudentsByName(name);
    }



    @GetMapping("/contain_name")
//    api/student/contain_name?name=T
    public List<Student> getStudentsWithNameChar(@RequestParam String name ){
        return studentService.getStudentNameWithChar(name);

    }

    @GetMapping("/program")
//    api/student/contain_name?name=T
    public List<Student> getStudentsByProgram(@RequestParam String program,@RequestParam int year ){
        return studentService.getStudentsByProgram(program,year);

    }


}
