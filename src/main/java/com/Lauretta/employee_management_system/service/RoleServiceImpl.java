package com.Lauretta.employee_management_system.service;

import com.Lauretta.employee_management_system.dto.RoleDto;
import com.Lauretta.employee_management_system.entity.Role;
import com.Lauretta.employee_management_system.exception.RecordNotFoundException;
import com.Lauretta.employee_management_system.mapper.RoleMapper;
import com.Lauretta.employee_management_system.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements GenericService<RoleDto, Long> {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleDto create(RoleDto roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDto(savedRole);
    }

    public RoleDto getById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Role not found with id: " + id));
        return roleMapper.toDto(role);
    }

    public RoleDto update(Long id, RoleDto roleDTO) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Role not found with id: " + id));
        roleMapper.updateFromDto(roleDTO, existingRole);
        Role updatedRole = roleRepository.save(existingRole);
        return roleMapper.toDto(updatedRole);
    }


    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RecordNotFoundException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }


    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<RoleDto> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }


}
