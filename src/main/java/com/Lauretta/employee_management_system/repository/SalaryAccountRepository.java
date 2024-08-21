package com.Lauretta.employee_management_system.repository;

import com.Lauretta.employee_management_system.entity.SalaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Long> {
}
