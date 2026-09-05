package com.negi.service.impl;

import com.negi.mapper.UserMapper;
import com.negi.model.User;
import com.negi.payload.dtos.UserDTO;
import com.negi.repository.UserRepository;
import com.negi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public UserDTO getUserByEmail(String email) throws Exception{
        User user= userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found with Email");
        }

        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) throws Exception {
        
        User user =userRepository.findById(id).orElseThrow(
            ()-> new Exception("user not found with id "+id)
        );

        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.toDTOList(users);
    }
}
