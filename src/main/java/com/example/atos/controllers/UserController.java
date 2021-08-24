package com.example.atos.controllers;

import com.example.atos.api.UserApi;
import com.example.atos.dto.UserDto;
import com.example.atos.exceptions.AtosException;
import com.example.atos.mappers.GenericMapper;
import com.example.atos.models.User;
import com.example.atos.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController implements UserApi {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        try {
            if (!isUserDataCorrect(userDto)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            User registeredUser = userService.register(GenericMapper.INSTANCE.asEntity(userDto));
            return new ResponseEntity<>(GenericMapper.INSTANCE.asDto(registeredUser), HttpStatus.OK);
        } catch (AtosException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @Override
    public ResponseEntity<UserDto> displayUser(@PathVariable Integer id) {
        if (id == null || id <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(GenericMapper.INSTANCE.asDto(userService.display(id)), HttpStatus.OK);
    }

    /**
     * check if mandatory data of user are set
     * @param userDto data of user
     * @return true if manadatory data are set, false otherwise
     */
    private boolean isUserDataCorrect(UserDto userDto){
        return userDto != null &&
                StringUtils.isNotBlank(userDto.getUsername()) &&
                userDto.getBirthdate() != null &&
                StringUtils.isNotBlank(userDto.getCountry());
    }
}
