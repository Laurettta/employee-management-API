package com.Lauretta.employee_management_system.service;

import com.Lauretta.employee_management_system.dto.SalaryDto;
import com.Lauretta.employee_management_system.entity.Employee;
import com.Lauretta.employee_management_system.mapper.EmployeeMapper;
import com.Lauretta.employee_management_system.repository.DepartmentRepository;
import com.Lauretta.employee_management_system.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SalaryServiceImpl implements SalaryService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public void updateSalary(SalaryDto salaryDto) {
        log.info("Updating salary for employeeId: {}", salaryDto.getEmployeeId());
        Employee employee = employeeRepository.findById(salaryDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + salaryDto.getEmployeeId()));
        employee.setSalary(salaryDto.getSalary());
        employeeRepository.save(employee);
    }

    @Override
    public SalaryDto getSalary(Long employeeId) {
        log.info("Fetching salary for employeeId: {}", employeeId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        return employeeMapper.employeeToSalaryDto(employee);
    }

    @Override
    public BigDecimal getAverageSalary(Long departmentId) {
        log.info("Calculating average salary for departmentId: {}", departmentId);
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        if (employees.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(employees.size()), BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public List<SalaryDto> getAllSalariesByDepartment(Long departmentId) {
        log.info("Fetching all salaries for departmentId: {}", departmentId);
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees.stream()
                .map(employeeMapper::employeeToSalaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalSalariesByDepartment(Long departmentId) {
        log.info("Calculating total salaries for departmentId: {}", departmentId);
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
