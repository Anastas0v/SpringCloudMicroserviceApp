package com.codespacelab.user.controller;

import com.codespacelab.user.model.dto.UserDTO;
import com.codespacelab.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;

    public UserController(final UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDTO> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping
    public UserDTO getUser(@RequestParam(name = "id") Long id)
    {
        return userService.getUser(id);
    }

    @PostMapping
    public UserDTO addUser(@Validated @RequestBody UserDTO userDTO)
    {
        return userService.addUser(userDTO);
    }

    @PutMapping
    public UserDTO updateUser(@Validated @RequestBody UserDTO userDTO)
    {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping
    public boolean deleteUser(@RequestParam(name = "id") Long id)
    {
        return userService.deleteUser(id);
    }

    @GetMapping("/validate")
    public boolean validateUser(@RequestParam(name = "id") Long id)
    {
        return userService.validateUser(id);
    }
}
