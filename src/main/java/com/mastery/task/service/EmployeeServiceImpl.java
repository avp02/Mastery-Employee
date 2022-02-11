package com.mastery.task.service;

import com.mastery.task.dao.EmployeeDAO;
import com.mastery.task.model.Employee;
import com.mastery.task.model.dto.EmployeeDto;
import com.mastery.task.model.mapper.EmployeeDtoMapper;
import com.mastery.task.model.mapper.EmployeeDtoMapperImpl;
import com.mastery.task.service.exception.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    private final EmployeeDtoMapper mapper = new EmployeeDtoMapperImpl();

    public EmployeeServiceImpl(EmployeeDAO employeeDAO, EmployeeDtoMapper mapper) {
        this.employeeDAO = employeeDAO;
//        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<EmployeeDto> findAll() {

        log.info("Method findAll started in class {}", getClass().getName());

        return employeeDAO.findAll()
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public EmployeeDto findById(Integer id) {

        log.info("Method findById with id = {} started in class {}", id, getClass().getName());

        return mapper.mapToDto(employeeDAO.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) {

        log.info("Method save with employee = {} started in class {}", employeeDto, getClass().getName());

        Employee employee = mapper.mapToEntity(employeeDto);

        return mapper.mapToDto(employeeDAO.save(employee));
    }

    @Override
    @Transactional
    public EmployeeDto update(Integer id, EmployeeDto employeeDto) {

        log.info("Method update with id = {} and with employee = {} started in class {}", id, employeeDto, getClass().getName());

        employeeDAO.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        Employee employee = mapper.mapToEntity(employeeDto);

        return mapper.mapToDto(employeeDAO.update(id, employee));
    }

    @Override
    @Transactional
    public EmployeeDto partialUpdate(Integer id, EmployeeDto employeeDto) {

        log.info("Method partialUpdate with id = {} and with employee = {} started in class {}", id, employeeDto, getClass().getName());

        Optional<Employee> optionalEmployee = employeeDAO.findById(id);
        optionalEmployee
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        Employee employee = optionalEmployee.get();
        mapper.update(employeeDto, employee);

        return mapper.mapToDto(employeeDAO.update(id, employee));
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        log.info("Method delete with id = {} started in class {}", id, getClass().getName());

        Optional<Employee> optionalEmployee = employeeDAO.findById(id);
        optionalEmployee.orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeDAO.delete(id);
    }
}
