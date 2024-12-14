package com.Lauretta.employee_management_system.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class DepartmentDto implements Serializable {
    private Long id;
    private String name;
}
