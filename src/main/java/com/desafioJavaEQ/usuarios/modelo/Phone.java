package com.desafioJavaEQ.usuarios.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String number;

    @NotBlank
    private String citycode;

    @NotBlank
    @Column(name = "contrycode")
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getContrycode() {
        return countrycode;
    }

    public void setContrycode(String contrycode) {
        this.countrycode = contrycode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
