package com.Lauretta.employee_management_system.service;

import java.util.List;

public interface GenericService <T, ID>{

    T create(T entity);

    T getById(ID id);

    T update(ID id, T entity);

    void delete(ID id);

    List<T> getAll();


}
