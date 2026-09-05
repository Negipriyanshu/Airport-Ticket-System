package com.negi.service;

import com.negi.payload.dtos.UserDTO;
import com.negi.payload.response.AuthResponse;



public interface AuthService {

    AuthResponse logIn(String email, String password) throws Exception;

    AuthResponse signUp(UserDTO req) throws Exception;
}
