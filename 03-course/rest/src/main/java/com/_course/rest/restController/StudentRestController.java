package com._course.rest.restController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com._course.rest.entity.Student;

@RestController
public class StudentRestController {

    @GetMapping("/students")
    public List<Student> getStudents(){

        //hard code for now... 
        //TODO connect to DB and retrieve students information

        List<Student> students = List.of(
            new Student("John","Doe"),
            new Student("Maria","Cara")
        );
        return students;
    }
}
