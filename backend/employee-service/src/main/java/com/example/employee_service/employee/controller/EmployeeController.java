package com.example.employee_service.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_service.employee.dto.CreateEmployeeRequest;
import com.example.employee_service.employee.dto.EmployeeResponse;
import com.example.employee_service.employee.dto.UpdateEmployeeRequest;
import com.example.employee_service.employee.dto.UpdateEmployeeStatusRequest;
import com.example.employee_service.employee.enums.EmployeeStatus;
import com.example.employee_service.employee.service.EmployeeService;
import com.example.employee_service.security.JwtAuthenticatedUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
@Validated
@Tag(name = "Employee Management", description = "Protected APIs for managing employee records")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/me")
    @Operation(summary = "Get current employee profile", description = "Returns employee profile matching the logged-in user's email")
    public ResponseEntity<EmployeeResponse> getCurrentEmployee(Authentication authentication) {
        JwtAuthenticatedUser currentUser = (JwtAuthenticatedUser) authentication.getPrincipal();
        return ResponseEntity.ok(employeeService.getCurrentEmployee(currentUser.email()));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @PostMapping
    @Operation(summary = "Create employee", description = "Creates a new employee record")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(request));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @GetMapping
    @Operation(summary = "Get all employees", description = "Returns all employee records")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get employee by id", description = "Returns a single employee by database id")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @GetMapping("/code/{employeeCode}")
    @Operation(summary = "Get employee by code", description = "Returns a single employee by employee code")
    public ResponseEntity<EmployeeResponse> getEmployeeByCode(@PathVariable String employeeCode) {
        return ResponseEntity.ok(employeeService.getEmployeeByCode(employeeCode));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @GetMapping("/department/{department}")
    @Operation(summary = "Get employees by department", description = "Returns employees for a given department")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartment(department));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @GetMapping("/status/{status}")
    @Operation(summary = "Get employees by status", description = "Returns employees filtered by current status")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByStatus(@PathVariable EmployeeStatus status) {
        return ResponseEntity.ok(employeeService.getEmployeesByStatus(status));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @PutMapping("/{id}")
    @Operation(summary = "Update employee", description = "Updates the full employee record")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEmployeeRequest request
    ) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @PatchMapping("/{id}/status")
    @Operation(summary = "Update employee status", description = "Updates only the employee status")
    public ResponseEntity<EmployeeResponse> updateEmployeeStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEmployeeStatusRequest request
    ) {
        return ResponseEntity.ok(employeeService.updateEmployeeStatus(id, request));
    }

    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee", description = "Deletes an employee record")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
