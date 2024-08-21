package com.Lauretta.employee_management_system.service;

import com.Lauretta.employee_management_system.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto findByIdAndName(Long id, String name);
}
