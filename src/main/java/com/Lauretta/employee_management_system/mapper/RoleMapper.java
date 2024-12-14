package com.Lauretta.employee_management_system.mapper;

import com.Lauretta.employee_management_system.dto.RoleDto;
import com.Lauretta.employee_management_system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto toDto(Role role);

    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleDto roleDTO);

    void updateFromDto(RoleDto roleDTO, @MappingTarget Role role);



}
