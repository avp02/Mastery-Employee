package com.mastery.task.model.mapper;

import com.mastery.task.model.Employee;
import com.mastery.task.model.dto.EmployeeDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeDtoMapper {

        EmployeeDto mapToDto(Employee employee);
        Employee mapToEntity(EmployeeDto dto);

        @BeanMapping(nullValuePropertyMappingStrategy =
                NullValuePropertyMappingStrategy.IGNORE)
        @Mapping(target = "id", ignore = true)
        void update(EmployeeDto dto, @MappingTarget Employee employee);
}
