package com.negi.model;

import com.negi.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;



import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "user_db")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;

    @Column(nullable = false)
    private UserRole role;

    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean isVerified;
}
