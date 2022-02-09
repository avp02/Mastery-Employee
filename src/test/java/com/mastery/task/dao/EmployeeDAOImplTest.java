package com.mastery.task.dao;

import com.mastery.task.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.mastery.task.model.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:data.sql")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class EmployeeDAOImplTest {

    @Autowired
    private EmployeeDAO employeeDAO;

    private Employee employee;

    @BeforeEach
    void setUp() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        employee = new Employee(1, "Aliko", "Dangote", 1, "Data scientist", MALE,
                LocalDate.parse("02-03-1995", df).atStartOfDay());
    }

    @Test
    @Order(1)
    void findAll() {
        log.info("Method findAll in class {}",getClass().getName());
        assertNotNull(employeeDAO);
        assertNotNull(employeeDAO.findAll());
    }

    @Test
    @Order(2)
    void shouldFindById() {
        log.info("Method shouldFindById in class {}",getClass().getName());

        Optional<Employee> expected = Optional.of(employee);

        Optional<Employee> actual = employeeDAO.findById(1);

        assertEquals(actual, expected);
    }

    @Test
    @Order(3)
    void shouldUpdate() {

        log.info("Method shouldUpdate in class {}",getClass().getName());

        employee.setFirstName("test");

        employeeDAO.update(1, employee);

        Optional<Employee> actual = employeeDAO.findById(1);
        assertEquals(employee.getFirstName(), actual.get().getFirstName());
    }

    @Test
    @Order(4)
    void shouldDelete() {

        log.info("Method shouldDelete in class {}",getClass().getName());

        employeeDAO.delete(1);

        Optional<Employee> actual = employeeDAO.findById(1);
        assertEquals(Optional.empty(), actual);
    }

    @Test
    @Order(5)
    void shouldSave() {
//
//        log.info("Method shouldSave in class {}",getClass().getName());
//
//        Employee expected = employeeDAO.save(employee);
//        Employee actual = employeeDAO.findById(expected.getEmployeeId()).get();
//
//        assertEquals(expected, actual);

    }
}