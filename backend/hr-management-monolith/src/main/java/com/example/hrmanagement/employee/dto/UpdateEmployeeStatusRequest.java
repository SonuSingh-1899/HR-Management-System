package com.example.hrmanagement.employee.dto;

import com.example.hrmanagement.employee.enums.EmployeeStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Payload for updating employee status")
public record UpdateEmployeeStatusRequest(
        @Schema(example = "ON_LEAVE")
        @NotNull(message = "Status is required")
        EmployeeStatus status
) {
}

