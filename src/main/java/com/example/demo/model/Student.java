package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Student — The MODEL layer ("The Order Form").
 *
 * This class serves two roles simultaneously:
 *
 *   1. JPA Entity  — Hibernate reads the @Entity and @Table annotations and
 *                    maps this class to a "students" table in the H2 database.
 *                    Every field becomes a column automatically.
 *
 *   2. DTO / Input — This is also the object Spring deserialises from the
 *                    incoming JSON request body. The @Valid annotation in the
 *                    Controller triggers the constraint annotations below
 *                    (@NotBlank, @Email) before the method body executes.
 *
 * Lombok annotations (require annotation processing to be enabled in your IDE):
 *   @Data               → generates getters, setters, toString, equals, hashCode
 *   @NoArgsConstructor  → generates Student() — required by JPA spec
 *   @AllArgsConstructor → generates Student(id, name, email) — convenient for tests
 */
@Entity
@Table(name = "students")   // Maps to the "students" table in H2
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * Primary key. @GeneratedValue with IDENTITY strategy means the database
     * auto-increments this value (1, 2, 3, …). You never set this manually.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Student's full name.
     *
     * @NotBlank rejects:
     *   - null values
     *   - empty strings ""
     *   - whitespace-only strings "   "
     *
     * If the incoming JSON has "name": "" or omits "name" entirely,
     * Spring returns HTTP 400 Bad Request with the message below.
     */
    @NotBlank(message = "Name cannot be empty")
    private String name;

    /**
     * Student's email address.
     *
     * @Email     — validates that the value looks like a proper email address.
     *              "notanemail" → rejected. "alex@example.com" → accepted.
     *
     * @Column(unique = true) — enforces uniqueness at the DATABASE level as well,
     *              so even if the Service layer check is bypassed, the DB will
     *              refuse a duplicate. nullable = false prevents NULL inserts at DB level.
     *
     * The Service layer ALSO checks for duplicates programmatically (see StudentService)
     * to give a friendlier error message than a raw DB constraint violation.
     */
    @Email(message = "Please provide a valid email address")
    @Column(unique = true, nullable = false)
    private String email;
}