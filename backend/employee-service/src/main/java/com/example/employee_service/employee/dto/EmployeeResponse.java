package com.example.employee_service.employee.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.employee_service.employee.enums.EmployeeStatus;
import com.example.employee_service.employee.enums.EmploymentType;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Employee response payload")
public record EmployeeResponse(
        @Schema(example = "1")
        Long id,
        @Schema(example = "EMP-1001")
        String employeeCode,
        @Schema(example = "Aman")
        String firstName,
        @Schema(example = "Sharma")
        String lastName,
        @Schema(example = "aman.sharma@company.com")
        String workEmail,
        @Schema(example = "9876543210")
        String phoneNumber,
        @Schema(example = "Engineering")
        String department,
        @Schema(example = "Software Engineer")
        String designation,
        @Schema(example = "FULL_TIME")
        EmploymentType employmentType,
        LocalDate joiningDate,
        @Schema(example = "65000.00")
        BigDecimal salary,
        @Schema(example = "Neha Singh")
        String managerName,
        @Schema(example = "Suresh Sharma")
        String emergencyContactName,
        @Schema(example = "9999999999")
        String emergencyContactPhone,
        @Schema(example = "Delhi, India")
        String address,
        @Schema(example = "ACTIVE")
        EmployeeStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
