package com.mastery.task.service;

import com.mastery.task.dao.EmployeeDAO;
import com.mastery.task.model.Employee;
import com.mastery.task.model.dto.EmployeeDto;
import com.mastery.task.service.exception.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static com.mastery.task.model.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @MockBean
    private EmployeeDAO employeeDAO;

    @Captor
    private ArgumentCaptor<Employee> employeeArgumentCaptor;

    private Employee employee;

    private EmployeeDto employeeDto;

    private Collection<Employee> employees;

    @BeforeEach
    void setUp() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        employee = new Employee(1, "Aliko", "Dangote", 1, "Data scientist", MALE,
                LocalDate.parse("02-03-1995", df).atStartOfDay());
        employeeDto = new EmployeeDto(1, "Aliko", "Dangote", 1, "Data scientist", "MALE",
                LocalDate.parse("02-03-1995", df).atStartOfDay());
        employees = new ArrayList<>();
        employees.add(employee);
    }

    @Test
    @Order(1)
    void shouldCorrectlyMapDtoToEntity() {

        log.info("Method shouldCorrectlyMapDtoToEntity in class {}", getClass().getName());

        employeeService.save(employeeDto);

        Mockito.verify(employeeDAO).save(employee);
    }

    @Test
    @Order(2)
    void shouldThrowExceptionWhenNotFound() {

        log.info("Method shouldThrowExceptionWhenNotFound in class {}", getClass().getName());

        when(employeeDAO.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.findById(3));
    }

    @Test
    @Order(3)
    void findAll() {

        log.info("Method findAll in class {}", getClass().getName());

        when(employeeDAO.findAll()).thenReturn(employees);

        Collection<EmployeeDto> employeeDtos = employeeService.findAll();

        verify(employeeDAO, times(1)).findAll();

        assertNotNull(employeeService.findAll());
        assertNotNull(employeeDtos);
    }

    @Test
    @Order(4)
    void save() {

        log.info("Method save in class {}", getClass().getName());

        when(employeeDAO.save(any(Employee.class))).thenReturn(employee);

        EmployeeDto employeeDto1 = employeeService.save(employeeDto);

        verify(employeeDAO, times(1)).save(eq(employee));

        assertNotNull(employeeDto1);
    }

    @Test
    @Order(5)
    void findById() {

        log.info("Method findById in class {}", getClass().getName());

        Integer id = employee.getEmployeeId();

        when(employeeDAO.findById(anyInt())).thenReturn(Optional.of(employee));

        EmployeeDto employeeDto = employeeService.findById(id);
        verify(employeeDAO, times(1)).findById(eq(id));

        assertNotNull(id);
        assertNotNull(employeeDto);
    }

    @Test
    @Order(6)
    void update() {

        log.info("Method findById in class {}", getClass().getName());

        Integer id = employee.getEmployeeId();

        when(employeeDAO.update(anyInt(), any(Employee.class))).thenReturn(employee);

        when(employeeDAO.findById(anyInt())).thenReturn(Optional.of(employee));

        EmployeeDto employeeDto1 = employeeService.update(id, employeeDto);
        verify(employeeDAO, times(1)).update(eq(id), employeeArgumentCaptor.capture());

        assertNotNull(employeeDto1);
    }

    @Test
    @Order(7)
    void partialUpdate() {

        log.info("Method partialUpdate in class {}", getClass().getName());

        Integer id = employee.getEmployeeId();

        when(employeeDAO.update(anyInt(), any(Employee.class))).thenReturn(employee);

        when(employeeDAO.findById(anyInt())).thenReturn(Optional.of(employee));

        EmployeeDto employeeDto1 = employeeService.update(id, employeeDto);
        verify(employeeDAO, times(1)).update(eq(id), employeeArgumentCaptor.capture());

        assertNotNull(id);
        assertNotNull(employeeDto1);
    }

    @Test
    @Order(8)
    void delete() {

        log.info("Method partialUpdate in class {}", getClass().getName());

        Integer id = employee.getEmployeeId();

        doNothing().when(employeeDAO).delete(anyInt());

        when(employeeDAO.findById(anyInt())).thenReturn(Optional.of(employee));

        employeeService.delete(id);

        assertNotNull(id);
    }
}