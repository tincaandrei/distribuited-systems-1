package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_role",
uniqueConstraints = @UniqueConstraint(
        name = "uk_role_name", columnNames = "role_name"
))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 32, nullable = false)
    private RoleName roleName;
}