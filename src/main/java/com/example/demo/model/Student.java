package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * The Student entity maps directly to the "students" table in the H2 database.
 *
 * Lombok annotations eliminate boilerplate:
 *   @Data            → generates getters, setters, toString, equals, hashCode
 *   @NoArgsConstructor  → generates a no-argument constructor (required by JPA)
 *   @AllArgsConstructor → generates a constructor with all fields
 *
 * Jakarta Validation annotations ensure data integrity before it hits the database.
 */
@Entity
@Table(name = "students")
@Data                   // Automatically generates getters, setters, and constructors
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Please provide a valid email address")
    @Column(unique = true, nullable = false) // Enforces uniqueness at the DB level too
    private String email;
}

