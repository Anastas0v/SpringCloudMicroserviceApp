package com.codespacelab.user.controller;

import com.codespacelab.user.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController
{
    @GetMapping("/all")
    public List<UserDTO> getUsers()
    {
        return new ArrayList<>();
    }

    @GetMapping
    public UserDTO getUser(@RequestParam(name = "id") Long id)
    {
        return UserDTO.builder().build();
    }

    @PostMapping
    public UserDTO addUser(@Validated @RequestBody UserDTO userDTO)
    {
        return UserDTO.builder().build();
    }

    @PutMapping
    public UserDTO updateUser(@Validated @RequestBody UserDTO userDTO)
    {
        return UserDTO.builder().build();
    }

    @DeleteMapping
    public boolean deleteUser(@RequestParam(name = "id") Long id)
    {
        return true;
    }

    @GetMapping("/validate")
    public boolean validateUser(@RequestParam(name = "id") Long id)
    {
        return true;
    }
}
