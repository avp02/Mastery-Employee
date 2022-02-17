package com.mastery.task.dao;

import com.mastery.task.model.Employee;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeDAO {

    Collection<Employee> findAll();

    Optional<Employee> findById(Integer id);

    Employee save(Employee employee);

    Employee update(Integer id, Employee employee);

    void delete(Integer id);
}
