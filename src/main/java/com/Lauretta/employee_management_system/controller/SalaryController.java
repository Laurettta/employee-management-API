package com.Lauretta.employee_management_system.controller;

import com.Lauretta.employee_management_system.dto.SalaryDto;
import com.Lauretta.employee_management_system.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/salaries")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;
    @PutMapping
    public ResponseEntity<Void> updateSalary(@RequestBody SalaryDto salaryDto) {
        salaryService.updateSalary(salaryDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<SalaryDto> getSalary(@PathVariable Long employeeId) {
        SalaryDto salaryDto = salaryService.getSalary(employeeId);
        return ResponseEntity.ok(salaryDto);
    }

    @GetMapping("/department/{departmentId}/average")
    public ResponseEntity<BigDecimal> getAverageSalary(@PathVariable Long departmentId) {
        BigDecimal averageSalary = salaryService.getAverageSalary(departmentId);
        return ResponseEntity.ok(averageSalary);
    }

    @GetMapping("/department/{departmentId}/all")
    public ResponseEntity<List<SalaryDto>> getAllSalariesByDepartment(@PathVariable Long departmentId) {
        List<SalaryDto> salaries = salaryService.getAllSalariesByDepartment(departmentId);
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/department/{departmentId}/total")
    public ResponseEntity<BigDecimal> getTotalSalariesByDepartment(@PathVariable Long departmentId) {
        BigDecimal totalSalaries = salaryService.getTotalSalariesByDepartment(departmentId);
        return ResponseEntity.ok(totalSalaries);
    }

}
