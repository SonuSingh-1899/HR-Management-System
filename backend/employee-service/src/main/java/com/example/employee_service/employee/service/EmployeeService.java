package com.example.employee_service.employee.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee_service.common.exception.BadRequestException;
import com.example.employee_service.common.exception.ResourceNotFoundException;
import com.example.employee_service.employee.dto.CreateEmployeeRequest;
import com.example.employee_service.employee.dto.EmployeeResponse;
import com.example.employee_service.employee.dto.UpdateEmployeeRequest;
import com.example.employee_service.employee.dto.UpdateEmployeeStatusRequest;
import com.example.employee_service.employee.entity.Employee;
import com.example.employee_service.employee.enums.EmployeeStatus;
import com.example.employee_service.employee.mapper.EmployeeMapper;
import com.example.employee_service.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        String normalizedCode = normalizeCode(request.employeeCode());
        String normalizedEmail = normalizeEmail(request.workEmail());

        if (employeeRepository.existsByEmployeeCode(normalizedCode)) {
            throw new BadRequestException("Employee with this code already exists");
        }
        if (employeeRepository.existsByWorkEmail(normalizedEmail)) {
            throw new BadRequestException("Employee with this work email already exists");
        }

        Employee employee = Employee.builder()
                .employeeCode(normalizedCode)
                .firstName(request.firstName().trim())
                .lastName(request.lastName().trim())
                .workEmail(normalizedEmail)
                .phoneNumber(request.phoneNumber().trim())
                .department(request.department().trim())
                .designation(request.designation().trim())
                .employmentType(request.employmentType())
                .joiningDate(request.joiningDate())
                .salary(request.salary())
                .managerName(request.managerName().trim())
                .emergencyContactName(request.emergencyContactName().trim())
                .emergencyContactPhone(request.emergencyContactPhone().trim())
                .address(request.address().trim())
                .status(request.status() == null ? EmployeeStatus.ACTIVE : request.status())
                .build();

        return EmployeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {
        return EmployeeMapper.toResponse(findEmployeeById(id));
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeByCode(String employeeCode) {
        return EmployeeMapper.toResponse(findEmployeeByCode(employeeCode));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartmentIgnoreCase(department.trim())
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getEmployeesByStatus(EmployeeStatus status) {
        return employeeRepository.findByStatus(status)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getCurrentEmployee(String userEmail) {
        Employee employee = employeeRepository.findByWorkEmail(normalizeEmail(userEmail))
                .orElseThrow(() -> new ResourceNotFoundException("Employee record not found for logged-in user"));
        return EmployeeMapper.toResponse(employee);
    }

    @Transactional
    public EmployeeResponse updateEmployee(Long id, UpdateEmployeeRequest request) {
        Employee employee = findEmployeeById(id);

        if (hasText(request.getEmployeeCode())) {
            String normalizedCode = normalizeCode(request.getEmployeeCode());
            if (!normalizedCode.equals(employee.getEmployeeCode()) && employeeRepository.existsByEmployeeCode(normalizedCode)) {
                throw new BadRequestException("Employee with this code already exists");
            }
            employee.setEmployeeCode(normalizedCode);
        }
        if (hasText(request.getFirstName())) {
            employee.setFirstName(request.getFirstName().trim());
        }
        if (hasText(request.getLastName())) {
            employee.setLastName(request.getLastName().trim());
        }
        if (hasText(request.getWorkEmail())) {
            String normalizedEmail = normalizeEmail(request.getWorkEmail());
            if (!normalizedEmail.equals(employee.getWorkEmail()) && employeeRepository.existsByWorkEmail(normalizedEmail)) {
                throw new BadRequestException("Employee with this work email already exists");
            }
            employee.setWorkEmail(normalizedEmail);
        }
        if (hasText(request.getPhoneNumber())) {
            employee.setPhoneNumber(request.getPhoneNumber().trim());
        }
        if (hasText(request.getDepartment())) {
            employee.setDepartment(request.getDepartment().trim());
        }
        if (hasText(request.getDesignation())) {
            employee.setDesignation(request.getDesignation().trim());
        }
        if (request.getEmploymentType() != null) {
            employee.setEmploymentType(request.getEmploymentType());
        }
        if (request.getJoiningDate() != null) {
            employee.setJoiningDate(request.getJoiningDate());
        }
        if (request.getSalary() != null) {
            employee.setSalary(request.getSalary());
        }
        if (hasText(request.getManagerName())) {
            employee.setManagerName(request.getManagerName().trim());
        }
        if (hasText(request.getEmergencyContactName())) {
            employee.setEmergencyContactName(request.getEmergencyContactName().trim());
        }
        if (hasText(request.getEmergencyContactPhone())) {
            employee.setEmergencyContactPhone(request.getEmergencyContactPhone().trim());
        }
        if (hasText(request.getAddress())) {
            employee.setAddress(request.getAddress().trim());
        }
        if (request.getStatus() != null) {
            employee.setStatus(request.getStatus());
        }

        return EmployeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Transactional
    public EmployeeResponse updateEmployeeStatus(Long id, UpdateEmployeeStatusRequest request) {
        Employee employee = findEmployeeById(id);
        employee.setStatus(request.status());
        return EmployeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.delete(findEmployeeById(id));
    }

    private Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    private Employee findEmployeeByCode(String employeeCode) {
        return employeeRepository.findByEmployeeCode(normalizeCode(employeeCode))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with code: " + employeeCode));
    }

    private String normalizeCode(String code) {
        return code == null ? null : code.trim().toUpperCase();
    }

    private String normalizeEmail(String email) {
        return email == null ? null : email.trim().toLowerCase();
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
