package com.mastery.task.service;

import com.mastery.task.model.dto.EmployeeDto;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeService {

    Collection<EmployeeDto> findAll();

    Optional<EmployeeDto> findById(Integer id);

    EmployeeDto save(EmployeeDto employeeDto);

    EmployeeDto update(Integer id, EmployeeDto employeeDto);

    void delete(Integer id);
}
