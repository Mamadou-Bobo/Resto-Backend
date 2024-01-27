package com.bobo.resto.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private Customer customer;

    @Column(nullable = false)
    private Date expiryDate;

    @Column(nullable = false)
    private Date issuedAt;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private boolean isTokenActive;

    public ResetPassword(String token,
                         Customer customer,
                         Date expiryDate,
                         Date issuedAt,
                         String code,
                         boolean isTokenActive) {
        this.token = token;
        this.customer = customer;
        this.expiryDate = expiryDate;
        this.issuedAt = issuedAt;
        this.code = code;
        this.isTokenActive = isTokenActive;
    }
}