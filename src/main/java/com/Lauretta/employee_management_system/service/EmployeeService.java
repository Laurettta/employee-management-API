package com.Lauretta.employee_management_system.service;

import com.Lauretta.employee_management_system.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService extends GenericService<EmployeeDto, Long>{

    List<EmployeeDto> searchEmployees(String name, Long departmentId, Long roleId);
}