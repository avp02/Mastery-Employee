package com.mastery.task.dao.mapper;

import com.mastery.task.model.Employee;
import com.mastery.task.model.Gender;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Slf4j
public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

//        log.info("Method mapRow get started from class {}", getClass().getName());

        return new Employee(
                rs.getInt("employee_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("department_id"),
                rs.getString("job_title"),
                Gender.genderById(rs.getInt("gender_id")),
                rs.getTimestamp("date_of_birth").toLocalDateTime()
        );
    }
}
