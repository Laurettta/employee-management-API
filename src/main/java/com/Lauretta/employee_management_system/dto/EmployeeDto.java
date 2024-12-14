package com.Lauretta.employee_management_system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
@Getter
@Setter
@Data
public class EmployeeDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Long departmentId;
    private Long roleId;
    private BigDecimal salary;

}
