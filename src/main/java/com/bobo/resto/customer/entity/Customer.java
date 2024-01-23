package com.bobo.resto.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "user_tbl")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @Email(
            message = "Email is not valid",
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
    )
    private String email;
    private String username;
    private String password;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(
            insertable = false
    )
    private LocalDateTime lastModified;

    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private Long createdBy;

    @LastModifiedBy
    @Column(
            insertable = false
    )
    private Long lastModifiedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private Collection<Role> roles;

    public Customer(String firstName,
                    String lastName,
                    String username,
                    String password,
                    String email,
                    Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}