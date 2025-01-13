package com.anuj.spoorthi.users;

import com.anuj.spoorthi.address.AddressEntity;
import com.anuj.spoorthi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phone")
})
public class UserEntity extends BaseEntity implements UserDetails {

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT 'FALSE'")
    private boolean isDeleted;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_Admin"),
                new SimpleGrantedAuthority("ROLE_Volunteer"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
