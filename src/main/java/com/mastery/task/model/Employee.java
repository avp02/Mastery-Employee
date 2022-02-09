package com.mastery.task.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer employeeId;

    private String firstName;

    private String lastName;

    private Integer departmentId;

    private String jobTitle;

    private Gender gender;

    private LocalDateTime dateOfBirth;

    public Employee setId(Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }
}
