package com.mar.events.services;

import com.mar.events.dtos.EmployeeDTO;
import com.mar.events.entities.Department;
import com.mar.events.entities.Employee;
import com.mar.events.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        Page<Employee> page = employeeRepository.findAll(pageable);
        return page.map(EmployeeDTO::new);
    }

    @Transactional
    public EmployeeDTO insert(EmployeeDTO employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(new Department(employeeDto.getDepartmentId(), null));
        employee = employeeRepository.save(employee);
        return new EmployeeDTO(employee);
    }
}
