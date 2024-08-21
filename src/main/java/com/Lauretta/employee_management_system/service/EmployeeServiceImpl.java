package com.Lauretta.employee_management_system.service;

import com.Lauretta.employee_management_system.dto.EmployeeDto;
import com.Lauretta.employee_management_system.entity.Department;
import com.Lauretta.employee_management_system.entity.Employee;
import com.Lauretta.employee_management_system.entity.Role;
import com.Lauretta.employee_management_system.entity.SalaryAccount;
import com.Lauretta.employee_management_system.exception.DuplicateEmployeeException;
import com.Lauretta.employee_management_system.exception.RecordNotFoundException;
import com.Lauretta.employee_management_system.mapper.EmployeeMapper;
import com.Lauretta.employee_management_system.repository.DepartmentRepository;
import com.Lauretta.employee_management_system.repository.EmployeeRepository;
import com.Lauretta.employee_management_system.repository.RoleRepository;
import com.Lauretta.employee_management_system.repository.SalaryAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final EmployeeMapper employeeMapper;
    private final SalaryAccountRepository salaryAccountRepository;


    @Override
    public void checkDuplicateEmployee(String name, String surname) {
        Optional<Employee> existingEmployee = employeeRepository.findByNameAndSurname(name, surname);
        if (existingEmployee.isPresent()) {
            throw new DuplicateEmployeeException("An employee with the same first name and last name already exists. Please add a patronymic.");
        }
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        log.info("Creating employee: {}", employeeDto);
        Employee employee = employeeMapper.toEntity(employeeDto);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getById(Long id) {
        log.info("Fetching employee by id: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", id);
                    return new RecordNotFoundException("Employee not found with id: " + id);
                });
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto employeeDto) {
        log.info("Updating employee with id: {}", id);
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", id);
                    return new RecordNotFoundException("Employee not found with id: " + id);
                });

        employeeMapper.updateFromDto(employeeDto, existingEmployee);
        return employeeMapper.toDto(employeeRepository.save(existingEmployee));
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting employee with id: {}", id);
        if (!employeeRepository.existsById(id)) {
            log.error("Employee not found with id: {}", id);
            throw new RecordNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAll() {
        log.info("Fetching all employees");
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> searchEmployees(String name, Long departmentId, Long roleId) {
        log.info("Searching employees with name: {}, departmentId: {}, roleId: {}", name, departmentId, roleId);
        if (name != null) {
            return employeeRepository.findByNameContaining(name).stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        } else if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> {
                        log.error("Department not found with id: {}", departmentId);
                        return new RecordNotFoundException("Department not found with id: " + departmentId);
                    });
            return employeeRepository.findByDepartment(department).stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        } else if (roleId != null) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> {
                        log.error("Role not found with id: {}", roleId);
                        return new RecordNotFoundException("Role not found with id: " + roleId);
                    });
            return employeeRepository.findByRole(role).stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
        }
        log.info("Returning all employees");
        return getAll();
    }


    @Override
    @Transactional
    public void creditSalariesToDepartment(Long departmentId, BigDecimal amount) {
        log.info("Crediting salaries to all employees in department with id: {}", departmentId);
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);

        for (Employee employee : employees) {
            SalaryAccount salaryAccount = new SalaryAccount();
            salaryAccount.setAmount(amount);
            salaryAccount.setEnrollmentDate(LocalDate.now());
            salaryAccount.setEmployee(employee);

            salaryAccountRepository.save(salaryAccount);
            log.info("Credited {} to employee with id: {}", amount, employee.getId());
        }
    }




}
