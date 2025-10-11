package com.mar.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mar.events.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
