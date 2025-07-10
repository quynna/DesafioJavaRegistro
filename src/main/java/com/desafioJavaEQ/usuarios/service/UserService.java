package com.desafioJavaEQ.usuarios.service;

import com.desafioJavaEQ.usuarios.modelo.Phone;
import com.desafioJavaEQ.usuarios.modelo.User;
import com.desafioJavaEQ.usuarios.Repositorio.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Value("${jwt.secret:mysecretkey}") // Usa "mysecretkey" por defecto si no está en application.properties
    private String jwtSecret;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email.toLowerCase());
    }

    @Transactional
    public User registerUser(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            System.out.println("===> Registrando usuario: " + user.getEmail());
            throw new IllegalArgumentException("El correo es obligatorio");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya registrado");
        }

        // Asegurar que la lista de teléfonos no sea nula
        if (user.getPhones() != null) {
            for (Phone phone : user.getPhones()) {
                phone.setUser(user);
            }

        }

        LocalDateTime now = LocalDateTime.now();

        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);
        user.setToken(generateToken(user.getEmail()));
        user.setActive(true);

        System.out.println("===> Guardando usuario con email: " + user.getEmail());
        System.out.println("===> Password: " + user.getPassword());
        System.out.println("===> Teléfonos: " + user.getPhones());
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 Esto imprime el error en consola
            throw new RuntimeException("Error al guardar usuario: " + e.getMessage());
        }

    }

    private String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .compact();
    }
}