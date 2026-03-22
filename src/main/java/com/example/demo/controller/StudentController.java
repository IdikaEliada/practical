package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> list() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
        // @Valid triggers the @Email and @NotBlank checks in the Model
        return new ResponseEntity<>(service.register(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}