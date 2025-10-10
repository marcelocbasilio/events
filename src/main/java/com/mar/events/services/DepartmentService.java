package com.mar.events.services;

import com.mar.events.dtos.DepartmentDTO;
import com.mar.events.entities.Department;
import com.mar.events.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentDTO> findAll() {
        List<Department> list = departmentRepository.findAll(Sort.by("name"));
        return list.stream().map(DepartmentDTO::new).toList();
    }
}
