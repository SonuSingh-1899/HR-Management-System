package com.example.hrmanagement.employee.mapper;

import com.example.hrmanagement.employee.dto.EmployeeResponse;
import com.example.hrmanagement.employee.entity.Employee;

public final class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getWorkEmail(),
                employee.getPhoneNumber(),
                employee.getDepartment(),
                employee.getDesignation(),
                employee.getEmploymentType(),
                employee.getJoiningDate(),
                employee.getSalary(),
                employee.getManagerName(),
                employee.getEmergencyContactName(),
                employee.getEmergencyContactPhone(),
                employee.getAddress(),
                employee.getStatus(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }
}

