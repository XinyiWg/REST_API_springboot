package com.example.studentManagement.mapper;

import com.example.studentManagement.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM student WHERE name LIKE #{name}")
    List<Student> getStudentsWithChar(@Param("name") String name);


//    select * from student where universityProgramId in (select id from university_program where year=2023)
    @Select("SELECT * FROM student WHERE university_program_id IN (SELECT id FROM university_program WHERE year = #{year} AND program = #{program});")
    List<Student> getStudentsInProgram(@Param("program") String program, @Param("year") Integer year);


}
