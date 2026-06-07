package com.example.hrmanagement.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hrmanagement.employee.entity.Employee;
import com.example.hrmanagement.employee.enums.EmployeeStatus;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeCode(String employeeCode);

    Optional<Employee> findByWorkEmail(String workEmail);

    List<Employee> findByDepartmentIgnoreCase(String department);

    List<Employee> findByStatus(EmployeeStatus status);

    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByWorkEmail(String workEmail);
}

