package com.mastery.task.service.exception;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(Integer id) {
        super(String.format("No employee with id = %d", id));
    }
}
