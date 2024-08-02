package com.Lauretta.employee_management_system.service;

import com.Lauretta.employee_management_system.dto.DepartmentDto;
import com.Lauretta.employee_management_system.entity.Department;
import com.Lauretta.employee_management_system.exception.RecordNotFoundException;
import com.Lauretta.employee_management_system.mapper.DepartmentMapper;
import com.Lauretta.employee_management_system.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements GenericService<DepartmentDto, Long>, DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentDto create(DepartmentDto departmentDto) {
        log.info("Creating department: {}", departmentDto);
        Department department = departmentMapper.toEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return departmentMapper.toDto(savedDepartment);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto getById(Long id) {
        log.info("Fetching department by id: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Department not found with id: {}", id);
                    return new RecordNotFoundException("Department not found with id: " + id);
                });
        return departmentMapper.toDto(department);
    }

    @Override
    public DepartmentDto update(Long id, DepartmentDto departmentDto) {
        log.info("Updating department with id: {}", id);
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Department not found with id: " + id));

        departmentMapper.updateFromDto(departmentDto, existingDepartment);
        Department updatedDepartment = departmentRepository.save(existingDepartment);
        return departmentMapper.toDto(updatedDepartment);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting department with id: {}", id);
        if (!departmentRepository.existsById(id)) {
            throw new RecordNotFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getAll() {
        log.info("Fetching all departments");
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public DepartmentDto findByIdAndName(Long id, String name) {
        Department department = departmentRepository.findByIdAndName(id, name);
        return departmentMapper.toDto(department);
    }

}
