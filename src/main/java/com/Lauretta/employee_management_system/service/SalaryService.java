package com.Lauretta.employee_management_system.service;

import com.Lauretta.employee_management_system.dto.SalaryDto;

import java.math.BigDecimal;
import java.util.List;

public interface SalaryService {

    void updateSalary(SalaryDto salaryDto);
    SalaryDto getSalary(Long employeeId);
    BigDecimal getAverageSalary(Long departmentId);
    List<SalaryDto> getAllSalariesByDepartment(Long departmentId);
    BigDecimal getTotalSalariesByDepartment(Long departmentId);
}
