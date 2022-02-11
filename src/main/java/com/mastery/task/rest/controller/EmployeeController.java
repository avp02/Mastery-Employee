package com.mastery.task.rest.controller;

import com.mastery.task.model.dto.EmployeeDto;
import com.mastery.task.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("employees")
@Slf4j
public class EmployeeController {

    @Value("${rest.host}")
    private String host;

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<EmployeeDto>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {

        EmployeeDto employeeDtoReturn = employeeService.save(employeeDto);

        return ResponseEntity.created(URI.create(host + "/employees/" + employeeDtoReturn.getEmployeeId()))
                .body(employeeDtoReturn);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable("id") Integer id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.update(id, employeeDto));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> partialUpdate(@PathVariable("id") Integer id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.partialUpdate(id, employeeDto));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        employeeService.delete(id);
    }

}
