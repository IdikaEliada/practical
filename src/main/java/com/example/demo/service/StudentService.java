package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * StudentService is the "Brain" of our application.
 *
 * It is responsible for:
 *   1. Retrieving data from the repository
 *   2. Enforcing business rules (e.g., no duplicate emails)
 *   3. Delegating persistence operations to the repository
 *
 * The Controller should NEVER talk to the Repository directly.
 * It must always go through the Service.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    /**
     * Retrieves all students from the database.
     * @return a list of all Student records
     */
    public List<Student> getAll() {
        return repository.findAll();
    }

    /**
     * Registers a new student after enforcing the duplicate-email business rule.
     *
     * Business Rule: No two students may share the same email address.
     * If the email is already taken, we throw a RuntimeException immediately
     * and the student is NOT saved.
     *
     * @param student the Student object received from the Controller
     * @return the saved Student (now with a generated ID)
     * @throws RuntimeException if the email address is already registered
     */
    public Student register(Student student) {
        // Logic: Check if email is already taken
        if (repository.findByEmail(student.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken!");
        }
        return repository.save(student);
    }

    /**
     * Deletes a student by their ID.
     *
     * Business Rule: You cannot delete a student that does not exist.
     *
     * @param id the ID of the student to delete
     * @throws RuntimeException if no student with that ID is found
     */
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Student not found.");
        }
        repository.deleteById(id);
    }
}