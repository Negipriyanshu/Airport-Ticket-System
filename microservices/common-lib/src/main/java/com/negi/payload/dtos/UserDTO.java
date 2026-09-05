package com.negi.payload.dtos;

import com.negi.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private UserRole role;
    private LocalDateTime lastLogin;
}
