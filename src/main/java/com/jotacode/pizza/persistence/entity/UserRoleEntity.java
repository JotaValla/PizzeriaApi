package com.jotacode.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserRoleEntity {

    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Id
    @Column(nullable = false, length = 20)
    private String role;

    @Column(name = "grant_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime grantedaDate;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private UserEntity user;

}
