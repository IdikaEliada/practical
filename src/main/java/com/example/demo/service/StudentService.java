package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student register(Student student) {
        // Logic: Check if email is already taken
        if (repository.findByEmail(student.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken!");
        }
        return repository.save(student);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Student not found.");
        }
        repository.deleteById(id);
    }
}