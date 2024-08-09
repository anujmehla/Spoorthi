package com.anuj.spoorthi.users;

import com.anuj.spoorthi.address.AddressEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phone")
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName" , nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "email",nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL, optional = true) // The relationship is optional
    @JoinColumn(name = "address_id", nullable = true) // Foreign key column is nullable
    private AddressEntity address;

    @Column(name = "deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT 'FALSE'")
    private boolean isDeleted;

}
