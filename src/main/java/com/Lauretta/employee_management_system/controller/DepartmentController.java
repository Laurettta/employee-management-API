package com.Lauretta.employee_management_system.controller;

import com.Lauretta.employee_management_system.dto.DepartmentDto;
import com.Lauretta.employee_management_system.service.DepartmentService;
import com.Lauretta.employee_management_system.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService depService;
    private final GenericService<DepartmentDto, Long> departmentService;

    @GetMapping
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAll();
    }

    @PostMapping
    public DepartmentDto createDepartment(@RequestBody DepartmentDto department) {
        return departmentService.create(department);
    }

    @GetMapping("/{id}")
    public DepartmentDto getDepartmentById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @PutMapping("/{id}")
    public DepartmentDto updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto department) {
        return departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
    }


}
