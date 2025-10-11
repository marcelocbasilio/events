package com.mar.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mar.events.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
