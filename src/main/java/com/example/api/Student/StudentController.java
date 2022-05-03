package com.example.api.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student) {
        studentService.registerStudent(student);
    }

    @DeleteMapping(path = "{StudentId}")
    public void deleteStudent(@PathVariable("StudentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{StudentId}")
    public void updateStudent(@PathVariable("StudentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }


}
