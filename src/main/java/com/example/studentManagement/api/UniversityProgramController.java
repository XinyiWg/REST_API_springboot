package com.example.studentManagement.api;

import com.example.studentManagement.exceptions.InvalidProgramException;
import com.example.studentManagement.exceptions.StudentEmptyNameException;
import com.example.studentManagement.model.Student;
import com.example.studentManagement.model.UniversityProgram;
import com.example.studentManagement.service.UniversityProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/programs")
public class UniversityProgramController {
    private UniversityProgramService universityProgramService;

    @Autowired

    public UniversityProgramController(UniversityProgramService universityProgramService) {
        this.universityProgramService = universityProgramService;
    }

    @GetMapping
    public List<UniversityProgram> getAllprograms(){
        return universityProgramService.getAllPrograms();
    }

    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<String> addProgram(@RequestBody UniversityProgram universityProgram){
        try{
            UniversityProgram p=universityProgramService.addProgram(universityProgram);
            return ResponseEntity.ok("ok: "+p.toString());
        }catch (InvalidProgramException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }
}
