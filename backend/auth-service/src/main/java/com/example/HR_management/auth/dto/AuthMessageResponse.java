package com.example.HR_management.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard authentication message response")
public record AuthMessageResponse(
        @Schema(example = "Authentication is required to access this resource")
        String message
) {
}
