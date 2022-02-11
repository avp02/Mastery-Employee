package com.mastery.task.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Integer employeeId;

    @NotNull(message = "First name cannot be empty")
    private String firstName;

    @NotNull(message = "First name cannot be empty")
    private String lastName;

    @Min(value = 0, message = "Department id has to be greater than 0")
    @NotNull(message= "Department id cannot be empty")
    private Integer departmentId;

    @NotNull(message = "Job title cannot be empty")
    private String jobTitle;

    @NotNull(message = "Gender cannot be empty")
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateOfBirth;
}
