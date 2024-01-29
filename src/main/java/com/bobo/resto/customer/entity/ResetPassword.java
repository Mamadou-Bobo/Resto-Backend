package com.bobo.resto.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Customer customer;

    @Column(nullable = false)
    private Date expiryDate;

    @Column(nullable = false)
    private Date issuedAt;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private boolean isTokenActive;

    public ResetPassword(Customer customer,
                         Date expiryDate,
                         Date issuedAt,
                         String code,
                         boolean isTokenActive) {
        this.customer = customer;
        this.expiryDate = expiryDate;
        this.issuedAt = issuedAt;
        this.code = code;
        this.isTokenActive = isTokenActive;
    }
}