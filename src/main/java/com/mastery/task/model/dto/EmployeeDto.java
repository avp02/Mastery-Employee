package com.mastery.task.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, updatable = false)
    private Integer employeeId;

    @NotNull(message = "First name cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "First name cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Min(value = 0, message = "Department id has to be greater than 0")
    @NotNull(message= "Department id cannot be empty")
    @Column(name = "department_id", nullable = false)
    private Integer departmentId;

    @NotNull(message = "Job title cannot be empty")
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @NotNull(message = "Gender cannot be empty")
    @Column(name = "gender")
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;
}
