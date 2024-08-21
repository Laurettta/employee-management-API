package com.Lauretta.employee_management_system.mapper;

import com.Lauretta.employee_management_system.dto.EmployeeDto;
import com.Lauretta.employee_management_system.dto.SalaryDto;
import com.Lauretta.employee_management_system.entity.Employee;
import com.Lauretta.employee_management_system.entity.SalaryAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "salaryAccounts", target = "salaryAccounts")
    EmployeeDto toDto(Employee employee);

    @Mapping(target = "department", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "salaryAccounts", target = "salaryAccounts")
    Employee toEntity(EmployeeDto employeeDTO);

    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleId", target = "role.id")
    @Mapping(source = "salary", target = "salary")
    void updateFromDto(EmployeeDto employeeDTO, @MappingTarget Employee employee);

    @Mapping(source = "employeeId", target = "id")
    @Mapping(source = "salary", target = "salary")
    Employee salaryDtoToEmployee(SalaryDto salaryDto);

    @Mapping(source = "id", target = "employeeId")
    @Mapping(source = "salary", target = "salary")
    SalaryDto employeeToSalaryDto(Employee employee);

//    // Add mappings for SalaryAccount and SalaryAccountDto
//    @Mapping(source = "employee.id", target = "id")
//    SalaryAccountDto toDto(SalaryAccount salaryAccount);
//
//    SalaryAccount toEntity(SalaryAccountDto salaryAccountDto);

}





