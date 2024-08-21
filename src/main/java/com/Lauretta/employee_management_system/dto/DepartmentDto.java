package com.Lauretta.employee_management_system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Data
@Getter
@Setter
public class DepartmentDto implements Serializable {
    private Long id;
    private String name;
}
