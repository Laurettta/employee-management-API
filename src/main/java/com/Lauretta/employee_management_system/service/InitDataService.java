package com.Lauretta.employee_management_system.service;

import com.Lauretta.employee_management_system.entity.Department;
import com.Lauretta.employee_management_system.entity.Employee;
import com.Lauretta.employee_management_system.entity.Role;
import com.Lauretta.employee_management_system.repository.DepartmentRepository;
import com.Lauretta.employee_management_system.repository.EmployeeRepository;
import com.Lauretta.employee_management_system.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class InitDataService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        // Create Roles
        Role adminRole = Role.builder().name("Admin").build();
        Role employeeRole = Role.builder().name("Employee").build();

        roleRepository.saveAll(Arrays.asList(adminRole, employeeRole));

        // Create Departments
        Department hrDept = Department.builder().name("HR Department").build();
        Department itDept = Department.builder().name("IT Department").build();

        departmentRepository.saveAll(Arrays.asList(hrDept, itDept));

        // Create Employees
        Employee employee1 = Employee.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .department(hrDept)
                .role(adminRole)
                .build();

        Employee employee2 = Employee.builder()
                .name("Jane Smith")
                .email("jane.smith@example.com")
                .password("password")
                .department(itDept)
                .role(employeeRole)
                .build();

        employeeRepository.saveAll(Arrays.asList(employee1, employee2));
    }
}
