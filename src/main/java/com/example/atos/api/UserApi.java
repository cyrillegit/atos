package com.example.atos.api;

import com.example.atos.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/atos/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserApi {
    /**
     * endpoint to register an user
     * @param userDto user to be registered
     * @return registered user
     */
    @PostMapping
    ResponseEntity<UserDto> registerUser(UserDto userDto);

    /**
     * get details of a user by its id
     * @param id of the user
     * @return details of the user
     */
    @GetMapping(value = "/{id}")
    ResponseEntity<UserDto> displayUser(Integer id);

}
