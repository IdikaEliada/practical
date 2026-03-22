package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * StudentController is the "Receptionist" of our application.
 *
 * It manages the Traffic of HTTP requests:
 *   - GET    /api/students        → list all students
 *   - POST   /api/students        → register a new student
 *   - DELETE /api/students/{id}   → remove a student by ID
 *
 * ResponseEntity gives us full control over the HTTP response,
 * including the status code and response body.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    /**
     * GET /api/students
     * Returns a list of all registered students.
     * HTTP 200 OK is returned automatically for @GetMapping.
     */
    @GetMapping
    public List<Student> list() {
        return service.getAll();
    }

    /**
     * POST /api/students
     * Registers a new student.
     *
     * @Valid triggers the @Email and @NotBlank checks defined in the Student Model.
     * If validation fails, Spring automatically returns HTTP 400 Bad Request
     * before this method body even executes.
     *
     * On success, returns HTTP 201 Created with the saved student in the body.
     */
    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
        // @Valid triggers the @Email and @NotBlank checks in the Model
        return new ResponseEntity<>(service.register(student), HttpStatus.CREATED);
    }

    /**
     * DELETE /api/students/{id}
     * Deletes a student by their ID.
     * Returns HTTP 200 OK with a confirmation message on success.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}