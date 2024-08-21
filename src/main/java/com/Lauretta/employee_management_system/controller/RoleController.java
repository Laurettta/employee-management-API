package com.Lauretta.employee_management_system.controller;

import com.Lauretta.employee_management_system.dto.RoleDto;
import com.Lauretta.employee_management_system.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final GenericService<RoleDto, Long> roleService;

    @GetMapping
    public List<RoleDto> getAllRoles() {
        return roleService.getAll();
    }

    @PostMapping
    public RoleDto createRole(@RequestBody RoleDto role) {
        return roleService.create(role);
    }

    @GetMapping("/{id}")
    public RoleDto getRoleById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @PutMapping("/{id}")
    public RoleDto updateRole(@PathVariable Long id, @RequestBody RoleDto role) {
        return roleService.update(id, role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.delete(id);
    }
}