package com.example.api.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void registerStudent(Student student) {
       Optional<Student> studentOptional = studentRepository.findByStudentByEmail(student.getEmail());
       if(studentOptional.isPresent()) {
           throw new IllegalArgumentException("Student already exists");
       }
        studentRepository.save(student);
    }


    public void deleteStudent(Long studentId) {
        boolean isStudentExists = studentRepository.existsById(studentId);
        if(!isStudentExists) {
            throw new IllegalStateException("Student does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(!studentOptional.isPresent()) {
            throw new IllegalStateException("Student does not exist");
        }
        Student student = studentOptional.get();
        if(name != null && !name.isEmpty() && !Objects.equals(name, studentOptional.get().getName())) {
            student.setName(name);
        }
        if(email != null && !email.isEmpty() && !Objects.equals(email, studentOptional.get().getEmail())) {
            Optional<Student> studentOptional1 = studentRepository.findByStudentByEmail(email);
            if(studentOptional1.isPresent()) {
                throw new IllegalArgumentException("Student already exists");
            }
            student.setEmail(email);
        }
    }
}
