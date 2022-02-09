package com.mastery.task.service;

import com.mastery.task.dao.EmployeeDAO;
import com.mastery.task.model.Employee;
import com.mastery.task.model.dto.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.mastery.task.model.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.MethodOrderer.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeDAO employeeRepository;

    private Employee employee;

    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        employee = new Employee(1, "Aliko", "Dangote", 1, "Data scientist", MALE,
                LocalDate.parse("02-03-1995", df).atStartOfDay());
        employeeDto = new EmployeeDto(1, "Aliko", "Dangote", 1, "Data scientist", "MALE",
                LocalDate.parse("02-03-1995", df).atStartOfDay());
    }

    @Test
    void shouldCorrectlyMapDtoToEntity() {
//        employeeService.save(employeeDto);
//
//        Mockito.verify(employeeRepository).save(employee);
    }

    @Test
    void findAll() {
//        Integer expected = 5;
//        Integer actual = employeeService.findAll().size();
//        assertEquals(expected, actual);
    }
}