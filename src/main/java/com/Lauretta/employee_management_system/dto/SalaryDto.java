package com.Lauretta.employee_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SalaryDto implements Serializable {
    private Long employeeId;
    private BigDecimal salary;
}
