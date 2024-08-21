package com.Lauretta.employee_management_system.repository;

import com.Lauretta.employee_management_system.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d WHERE d.name = :name AND d.id = :id")
    Department findByIdAndName(@Param("id") Long id, @Param("name") String name);
}
