package com.Lauretta.employee_management_system.repository;

import com.Lauretta.employee_management_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
