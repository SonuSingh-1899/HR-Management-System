package com.example.hrmanagement.employee.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.hrmanagement.employee.enums.EmployeeStatus;
import com.example.hrmanagement.employee.enums.EmploymentType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Schema(description = "Payload for creating a new employee")
public record CreateEmployeeRequest(
        @Schema(example = "EMP-1001")
        @NotBlank(message = "Employee code is required")
        String employeeCode,
        @Schema(example = "Aman")
        @NotBlank(message = "First name is required")
        String firstName,
        @Schema(example = "Sharma")
        @NotBlank(message = "Last name is required")
        String lastName,
        @Schema(example = "aman.sharma@company.com")
        @Email(message = "Invalid work email format")
        @NotBlank(message = "Work email is required")
        String workEmail,
        @Schema(example = "9876543210")
        @NotBlank(message = "Phone number is required")
        String phoneNumber,
        @Schema(example = "Engineering")
        @NotBlank(message = "Department is required")
        String department,
        @Schema(example = "Software Engineer")
        @NotBlank(message = "Designation is required")
        String designation,
        @Schema(example = "FULL_TIME")
        @NotNull(message = "Employment type is required")
        EmploymentType employmentType,
        @NotNull(message = "Joining date is required")
        @PastOrPresent(message = "Joining date cannot be in the future")
        LocalDate joiningDate,
        @NotNull(message = "Salary is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero")
        BigDecimal salary,
        @Schema(example = "Neha Singh")
        @NotBlank(message = "Manager name is required")
        String managerName,
        @Schema(example = "Suresh Sharma")
        @NotBlank(message = "Emergency contact name is required")
        String emergencyContactName,
        @Schema(example = "9999999999")
        @NotBlank(message = "Emergency contact phone is required")
        String emergencyContactPhone,
        @Schema(example = "Delhi, India")
        @NotBlank(message = "Address is required")
        String address,
        @Schema(example = "ACTIVE")
        EmployeeStatus status
) {
}

