package com.mastery.task.dao;

import com.mastery.task.dao.mapper.EmployeeRowMapper;
import com.mastery.task.model.Employee;
import com.mastery.task.model.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class EmployeeDAOImpl implements EmployeeDAO{

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withSchemaName("employeedb.public")
                .withTableName("\"‘employee’ \"")
                .usingColumns("first_name", "last_name", "department_id",
                        "job_title", "gender_id", "date_of_birth")
                .usingGeneratedKeyColumns("employee_id");
    }

    @Value("${sql.find.all}")
    public String findAll;

    @Value("${sql.find.by.id}")
    public String findById;

    @Value("${sql.update}")
    private String update;

    @Value("${sql.delete}")
    private String delete;

    @Override
    public Collection<Employee> findAll() {

        log.info("Method findAll started in class {}", getClass().getName());

        return jdbcTemplate.query(findAll, new EmployeeRowMapper());
    }

    @Override
    public Optional<Employee> findById(Integer id) {

        log.info("Method findById with id = {} started in class {}", id, getClass().getName());

        return jdbcTemplate.query(
                        findById, new Object[]{id}, new EmployeeRowMapper())
                .stream().findAny();
    }

    @Override
    public Employee save(Employee employee) {

        log.info("Method save with employee = {} started in class {}", employee, getClass().getName());

//        SqlParameterSource params = new MapSqlParameterSource()
//                .addValue("first_name", employee.getFirstName())
//                .addValue("last_name", employee.getLastName())
//                .addValue("department_id", employee.getDepartmentId())
//                .addValue("job_title", employee.getJobTitle())
//                .addValue("gender_id", employee.getGender().getId())
//                .addValue("date_of_birth", employee.getDateOfBirth());
        HashMap<String, Object> params = new HashMap<>();
        params.put("first_name", employee.getFirstName());
        params.put("last_name", employee.getLastName());
        params.put("department_id", employee.getDepartmentId());
        params.put("job_title", employee.getJobTitle());
        params.put("gender_id", employee.getGender().getId());
        params.put("date_of_birth", employee.getDateOfBirth());

        Number employeeId = jdbcInsert.executeAndReturnKey(params
//                Map.of("first_name", employee.getFirstName(),
//                        "last_name", employee.getLastName(),
//                        "department_id", employee.getDepartmentId(),
//                        "job_title", employee.getJobTitle(),
//                        "gender_id", employee.getGender().getId(),
//                        "date_of_birth", employee.getDateOfBirth())
        );
//        Number employeeId = jdbcInsert.executeAndReturnKey(params);

        return employee.setId(employeeId.intValue());
    }

    @Override
    public Employee update(Integer id, Employee employee) {

        log.info("Method update with id = {} and with employee = {} started in class {}", id, employee, getClass().getName());

        jdbcTemplate.update(
                update,
                employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(),
                employee.getJobTitle(), employee.getGender().getId(), employee.getDateOfBirth(), id);

        return employee.setId(id);
    }

    @Override
    public void delete(Integer id) {

        log.info("Method delete with id = {} started in class {}", id, getClass().getName());

        jdbcTemplate.update(delete, id);
    }
}
