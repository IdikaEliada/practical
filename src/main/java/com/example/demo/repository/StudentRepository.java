package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * StudentRepository is our "Storage Manager."
 *
 * By extending JpaRepository<Student, Long>, we automatically get:
 *   - findAll()       → retrieve all students
 *   - findById(id)    → find a student by their ID
 *   - save(student)   → insert or update a student
 *   - deleteById(id)  → delete a student by their ID
 *   - existsById(id)  → check if a student exists
 *
 * No SQL needed. Spring Data JPA writes it for us.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom Search: Spring reads the method name and writes the SQL automatically!
    // This translates to: SELECT * FROM students WHERE email = ?
    Optional<Student> findByEmail(String email);
}
