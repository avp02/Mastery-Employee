package com.mastery.task.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.task.model.dto.EmployeeDto;
import com.mastery.task.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@Slf4j
class EmployeeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void givenListOfEmployee_whenGetEmployees_thenStatus200() throws Exception {

        log.info("Method givenListOfEmployee_whenGetEmployees_thenStatus200 in class {}",
                getClass().getName());

        when(employeeService.findAll()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(create(0),
                        create(1)))));
    }

    @Test
    void givenEmployeeById_whenGetEmployee_thenReturn200() throws Exception {

        log.info("Method givenEmployeeById_whenGetEmployee_thenReturn200 in class {}",
                getClass().getName());

        when(employeeService.findById(anyInt())).thenReturn(create(0));

        mockMvc.perform(get("/employees/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(0))
                .andExpect(jsonPath("$.firstName").value("firstName0"))
                .andExpect(content().json(objectMapper.writeValueAsString(create(0))));
    }

    @Test
    void saveEmployee_whenSavedEmployee_thenReturn201() throws Exception {

        log.info("Method saveEmployee_whenSavedEmployee_thenReturn201 in class {}", getClass().getName());

        when(employeeService.save(any(EmployeeDto.class))).thenReturn(create(0));

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(create(0)))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.employeeId").value(0))
                .andExpect(jsonPath("$.firstName").value("firstName0"))
                .andExpect(content().json(objectMapper.writeValueAsString(create(0))));
    }

    @Test
    void givenEmployee_whenUpdateEmployee_thenReturn200() throws Exception {

        log.info("Method givenEmployee_whenUpdateEmployee_thenReturn200 in class {}", getClass().getName());

        when(employeeService.update(anyInt(), any(EmployeeDto.class))).thenReturn(create(1));

        mockMvc.perform(put("/employees/" + create(1).getEmployeeId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(create(1)))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.employeeId").value(1))
                .andExpect(jsonPath("$.firstName").value("firstName1"))
                .andExpect(content().json(objectMapper.writeValueAsString(create(1))));
    }

    @Test
    void givenEmployee_whenPartialUpdateEmployee_thenReturn200() throws Exception {

        log.info("Method givenEmployee_whenPartialUpdateEmployee_thenReturn200 in class {}",
                getClass().getName());

        when(employeeService.partialUpdate(anyInt(), any(EmployeeDto.class))).thenReturn(create(0));

        mockMvc.perform(patch("/employees/" + create(0).getEmployeeId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(create(0)))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.employeeId").value(0))
                .andExpect(jsonPath("$.firstName").value("firstName0"))
                .andExpect(content().json(objectMapper.writeValueAsString(create(0))));
    }

    @Test
    void givenNoContent_whenDeleteEmployeeById_thenReturn204() throws Exception {

        log.info("Method givenNoContent_whenDeleteEmployeeById_thenReturn204 in class {}",
                getClass().getName());

        when(employeeService.findById(anyInt())).thenReturn(create(0));

        mockMvc.perform(delete("/employees/0"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private EmployeeDto create(Integer index) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(index);
        employeeDto.setFirstName("firstName" + index);
        employeeDto.setLastName("lastName" + index);
        employeeDto.setDepartmentId(1 + index);
        employeeDto.setJobTitle("jobTitle" + index);
        employeeDto.setGender("GENDER" + index);
        employeeDto.setDateOfBirth(LocalDateTime.parse(new StringBuilder("1970-01-01T00:00:0")
                .append(index)));
        return employeeDto;
    }
}