package com.digital.tms.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "user_entity",
        uniqueConstraints = {@UniqueConstraint(name = "email_unique", columnNames = "email")},
        indexes = {@Index(name = "email_index", columnList = "email")}
)
public class UserEntity extends AbstractPersistable<Long> {
    @Column(name = "email", nullable = false)
    private String email;

    private String firstname;

    private String lastname;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<ThermostatEntity> thermostats = new ArrayList<>();

    private boolean isAccountNonExpired;
}
