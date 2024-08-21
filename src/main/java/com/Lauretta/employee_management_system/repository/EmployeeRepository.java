package com.Lauretta.employee_management_system.repository;

import com.Lauretta.employee_management_system.entity.Department;
import com.Lauretta.employee_management_system.entity.Employee;
import com.Lauretta.employee_management_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment(Department department);

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByRole(Role role);

    List<Employee> findByNameContaining(String name);

    Optional<Employee> findByNameAndSurname(String name, String surname);



}
