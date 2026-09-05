package com.negi.service.impl;

import com.negi.config.JwtProvider;
import com.negi.enums.UserRole;
import com.negi.mapper.UserMapper;
import com.negi.model.User;
import com.negi.payload.dtos.UserDTO;
import com.negi.payload.response.AuthResponse;
import com.negi.repository.UserRepository;
import com.negi.service.AuthService;
import com.negi.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;
    
    /*
    1. Check if email already exists
    2. Encode password using BCrypt
    3. Save user in  DB
    4. Generate JWT token
    5. Return token and user information
    */
@Override
    public AuthResponse signUp(UserDTO req) throws Exception
    {
        User existingUser = userRepository.findByEmail(req.getEmail());

        // is user existed or not
        if(existingUser!=null)
        {
            throw new Exception("email already registered...");
        }

        // check user role is not SYSTEM_ADMIN
        if(req.getRole()== UserRole.ROLE_SYSTEM_ADMIN)
        {
            throw new Exception("You cannot signup with system admins!");
        }

        // Mapping UserDTO to User entity
        User newUser= User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(req.getRole())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isVerified(false)
                .build();

        // save the new user record in DB
        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),savedUser.getPassword()
        );

        String jwt = jwtProvider.generateToken(
                authentication,savedUser.getId()
        );

        // method response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(newUser));
        authResponse.setTitle("Welcome "+savedUser.getFullName());
        authResponse.setMessage("Registered Successfully");
        return authResponse;

}

    /*
        1. Lod user by email
        2. Compare password with BCrypt
        3. Update 'lastLogin' time
        4. Generate JWT token
        5. Return token and user information
    */
    @Override
    public AuthResponse logIn(String email, String password) throws Exception {

        // to authenticate the user
        Authentication authentication = authenticate(email,password);

        User user =userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());     // update the last login details
        user.setIsVerified(true);
        userRepository.save(user);

        String jwt = jwtProvider.generateToken(authentication,user.getId());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(user));
        authResponse.setTitle("Welcome "+user.getFullName());
        authResponse.setMessage("Login Successfully");
        return authResponse;

    }

    private Authentication authenticate(String email, String password) throws Exception {

        //load the user
        UserDetails userDetails= customUserDetailsService.loadUserByUsername(email);
        //matches the password
        if(!passwordEncoder.matches(password, userDetails.getPassword()))
        {
         throw new Exception("Invalid Password");
        }

        // return authentiction
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }


}
