package com.desafioJavaEQ.usuarios.controller;

import com.desafioJavaEQ.usuarios.modelo.User;
import com.desafioJavaEQ.usuarios.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    private final UserService userService;

    // Constructor con inyección de dependencia
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (IllegalArgumentException e) {
            return error(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return error("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Map<String, String>> error(String mensaje, HttpStatus status) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", mensaje);
        return new ResponseEntity<>(response, status);
    }
}
