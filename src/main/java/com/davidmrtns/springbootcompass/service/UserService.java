package com.davidmrtns.springbootcompass.service;

import com.davidmrtns.springbootcompass.dto.UserDTO;
import com.davidmrtns.springbootcompass.entity.UserEntity;
import com.davidmrtns.springbootcompass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<UserDTO> list(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public void insert(UserDTO userDTO){
        UserEntity user = new UserEntity(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }

    public UserDTO update(UserDTO userDTO){
        UserEntity user = new UserEntity(userDTO);
        return new UserDTO(userRepository.save(user));
    }

    public void delete(Long id){
        UserEntity user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    public UserDTO searchById(Long id){
        UserEntity user = userRepository.findById(id).get();
        return new UserDTO(user);
    }
}
