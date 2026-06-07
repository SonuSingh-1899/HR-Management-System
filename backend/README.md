# Backend

This folder now builds a single monolithic Spring Boot backend for the project.

## Application

- `hr-management-monolith`
  - Handles authentication, user management, and employee management APIs
  - Default port: `8080`
  - API base paths: `/api/auth/**`, `/api/users/**`, `/api/employees/**`

## Run From `backend`

Run the monolith:

```powershell
mvn -pl hr-management-monolith -DskipTests spring-boot:run
```

Run compile check:

```powershell
mvn -pl hr-management-monolith -DskipTests compile
```
