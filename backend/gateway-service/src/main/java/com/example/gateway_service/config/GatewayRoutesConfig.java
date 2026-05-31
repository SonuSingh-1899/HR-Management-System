package com.example.gateway_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    /**
     * This bean defines the entry points that the frontend should call.
     *
     * Why we use /api/... on the gateway:
     * - The frontend talks to only one server: gateway-service.
     * - The gateway then forwards the request to the correct backend service.
     * - This keeps the frontend simple and hides internal service URLs.
     *
     * Route summary:
     * - /api/auth/**       -> auth-service
     * - /api/users/**      -> auth-service
     * - /api/employees/**  -> employee-service
     * - /docs/auth         -> auth-service swagger
     * - /docs/employee     -> employee-service swagger
     */
    @Bean
    public RouteLocator gatewayRoutes(
            RouteLocatorBuilder builder,
            @Value("${services.auth.url}") String authServiceUrl,
            @Value("${services.employee.url}") String employeeServiceUrl
    ) {
        return builder.routes()
                .route("auth-service-auth-api", route -> route
                        .path("/api/auth/**")
                        // StripPrefix=1 removes "/api" before forwarding.
                        // Example:
                        // gateway receives: /api/auth/login
                        // auth-service gets: /auth/login
                        .filters(filter -> filter.stripPrefix(1))
                        .uri(authServiceUrl))

                .route("auth-service-user-api", route -> route
                        .path("/api/users/**")
                        .filters(filter -> filter.stripPrefix(1))
                        .uri(authServiceUrl))

                .route("employee-service-api", route -> route
                        .path("/api/employees/**")
                        .filters(filter -> filter.stripPrefix(1))
                        .uri(employeeServiceUrl))

                .route("auth-service-swagger", route -> route
                        .path("/docs/auth")
                        // SetPath maps this simple gateway URL to the downstream
                        // swagger UI entry point.
                        .filters(filter -> filter.setPath("/swagger-ui.html"))
                        .uri(authServiceUrl))

                .route("employee-service-swagger", route -> route
                        .path("/docs/employee")
                        .filters(filter -> filter.setPath("/swagger-ui.html"))
                        .uri(employeeServiceUrl))

                .route("auth-service-openapi", route -> route
                        .path("/docs/auth/v3/api-docs")
                        .filters(filter -> filter.setPath("/v3/api-docs"))
                        .uri(authServiceUrl))

                .route("employee-service-openapi", route -> route
                        .path("/docs/employee/v3/api-docs")
                        .filters(filter -> filter.setPath("/v3/api-docs"))
                        .uri(employeeServiceUrl))

                .build();
    }
}
