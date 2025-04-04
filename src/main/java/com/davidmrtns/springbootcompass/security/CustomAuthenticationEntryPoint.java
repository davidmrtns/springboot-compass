package com.davidmrtns.springbootcompass.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setContentType("application/json");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if (authException instanceof DisabledException ||
                authException instanceof LockedException ||
                authException instanceof AccountExpiredException ||
                authException instanceof CredentialsExpiredException
        ) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", authException.getMessage());

        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
}
