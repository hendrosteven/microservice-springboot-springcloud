package com.kelaskoding.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    public static final List<String> apiEndpoint = List.of(
            "/api/auth/register", "/api/auth/generateToken",
            "/api/auth/validateToken", "/eureka");

    public Predicate<ServerHttpRequest> isSecured = request -> apiEndpoint.stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
