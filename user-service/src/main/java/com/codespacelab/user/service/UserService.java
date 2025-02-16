package com.codespacelab.user.service;

import com.codespacelab.user.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService
{
    private List<UserDTO> users;

    public UserService()
    {
        UserDTO user1 = new UserDTO(123L, "Test", true);
        UserDTO user2 = new UserDTO(456L, "Test2", false);
        users = new ArrayList<>(Arrays.asList(user1, user2));
    }

    public List<UserDTO> getUsers()
    {
        return users;
    }

    public UserDTO getUser(Long id)
    {
        Optional<UserDTO> user = users.stream().filter(userDTO -> userDTO.getId() == id).findFirst();
        return user.orElse(null);
    }

    public UserDTO addUser(UserDTO userDTO)
    {
        UserDTO userDTO1 = new UserDTO(users.size() + 100L, userDTO.getName(), true);
        users.add(userDTO1);
        return userDTO1;
    }

    public UserDTO updateUser(UserDTO userDTO)
    {
        Optional<UserDTO> userDTOOptional = users.stream()
                .filter(user -> user.getId() == userDTO.getId())
                .map(user -> {
                    user.setName(userDTO.getName());
                    user.setActive(userDTO.isActive());
                    return user;
                }).findFirst();
        return userDTOOptional.orElse(null);
    }

    public boolean deleteUser(Long id)
    {
        final int originalSize = users.size();
        users = users.stream().filter(user -> !user.getId().equals(id)).collect(Collectors.toList());

        return originalSize > users.size();
    }

    public boolean validateUser(Long id)
    {
        Optional<UserDTO> user = users.stream().filter(userDTO -> userDTO.getId() == id).findFirst();
        return user.map(UserDTO::isActive).orElse(false);
    }
}
