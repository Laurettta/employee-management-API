package com.Lauretta.employee_management_system.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Long departmentId;
    private Long roleId;

}
