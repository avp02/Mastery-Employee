package com.mastery.task.service;

import com.mastery.task.dao.EmployeeDAO;
import com.mastery.task.model.dto.EmployeeDto;
import com.mastery.task.model.mapper.EmployeeDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeDAO employeeRepository;

    private final EmployeeDtoMapper mapper;

    public EmployeeServiceImpl(EmployeeDAO employeeRepository, EmployeeDtoMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<EmployeeDto> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public EmployeeDto update(Integer id, EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
