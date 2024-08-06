package com.Lauretta.employee_management_system.mapper;

import com.Lauretta.employee_management_system.dto.DepartmentDto;
import com.Lauretta.employee_management_system.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(DepartmentMapper.class);

//    @Mapping(target = "id", ignore = true)
    Department toEntity(DepartmentDto departmentDTO);

    DepartmentDto toDto(Department department);

    void updateFromDto(DepartmentDto departmentDTO, @MappingTarget Department department);
}
