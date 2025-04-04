package com.davidmrtns.springbootcompass.controller;

import java.util.List;

import com.davidmrtns.springbootcompass.dto.UserDTO;
import com.davidmrtns.springbootcompass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> list(){
        return userService.list();
    }

    @PostMapping
    public void insert(@RequestBody UserDTO user){
        userService.insert(user);
    }

    @PutMapping
    public UserDTO update(@RequestBody UserDTO user){
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
