package com.codespacelab.order.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class UserService
{
    private final UserClient userClient;

    public UserService(UserClient userClient)
    {
        this.userClient = userClient;
    }

    public boolean isUserValid(Long id)
    {
        boolean isValid = userClient.isUserValid(id);

        if (!isValid)
        {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Invalid User!");
        }

        return isValid;
    }
}
