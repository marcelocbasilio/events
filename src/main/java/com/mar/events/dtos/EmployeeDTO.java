package com.mar.events.dtos;

import com.mar.events.entities.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Field required")
    private String name;

    @Email(message = "Invalid e-mail")
    private String email;

    @NotNull(message = "Field required")
    private Long departmentId;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name, String email, Long departmentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
    }

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        email = employee.getEmail();
        departmentId = employee.getDepartment().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
