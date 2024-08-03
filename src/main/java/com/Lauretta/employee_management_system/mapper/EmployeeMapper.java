package com.Lauretta.employee_management_system.mapper;

import com.Lauretta.employee_management_system.dto.EmployeeDto;
import com.Lauretta.employee_management_system.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "role.id", target = "roleId")
    EmployeeDto toDto(Employee employee);

    @Mapping(target = "department", ignore = true)
    @Mapping(target = "role", ignore = true)
    Employee toEntity(EmployeeDto employeeDto);

    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleId", target = "role.id")
    void updateFromDto(EmployeeDto employeeDto, @MappingTarget Employee employee);
}





