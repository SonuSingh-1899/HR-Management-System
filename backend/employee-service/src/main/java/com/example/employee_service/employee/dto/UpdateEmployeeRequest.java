package com.example.employee_service.employee.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.employee_service.employee.enums.EmployeeStatus;
import com.example.employee_service.employee.enums.EmploymentType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Payload for updating an employee")
public class UpdateEmployeeRequest {

    @Schema(example = "EMP-1001")
    private String employeeCode;

    @Schema(example = "Aman")
    private String firstName;

    @Schema(example = "Sharma")
    private String lastName;

    @Schema(example = "aman.sharma@company.com")
    @Email(message = "Invalid work email format")
    private String workEmail;

    @Schema(example = "9876543210")
    private String phoneNumber;

    @Schema(example = "Engineering")
    private String department;

    @Schema(example = "Software Engineer")
    private String designation;

    @Schema(example = "FULL_TIME")
    private EmploymentType employmentType;

    @PastOrPresent(message = "Joining date cannot be in the future")
    private LocalDate joiningDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero")
    private BigDecimal salary;

    @Schema(example = "Neha Singh")
    private String managerName;

    @Schema(example = "Suresh Sharma")
    private String emergencyContactName;

    @Schema(example = "9999999999")
    private String emergencyContactPhone;

    @Schema(example = "Delhi, India")
    private String address;

    @Schema(example = "ACTIVE")
    private EmployeeStatus status;
}
