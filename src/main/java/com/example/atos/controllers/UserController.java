package com.example.atos.controllers;

import com.example.atos.api.UserApi;
import com.example.atos.dto.ResultDto;
import com.example.atos.dto.UserDto;
import com.example.atos.exceptions.AtosException;
import com.example.atos.helpers.DateHelper;
import com.example.atos.mappers.UserMapper;
import com.example.atos.models.User;
import com.example.atos.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private ResultDto resultDto;

    @Value("${adult.age.french}")
    private int adultAge;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<ResultDto> registerUser(@RequestBody UserDto userDto) {
        try {
            resultDto = new ResultDto();
            if (!isUserDataCorrect(userDto)) {
                resultDto.setMessage("User cannot be registered because its data are invalid");
                resultDto.setHttpStatus(HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(resultDto, HttpStatus.BAD_REQUEST);
            }

            if (!isUserAgeCorrect(userDto)){
                resultDto.setMessage("User cannot be registered because not an adult");
                resultDto.setHttpStatus(HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(resultDto, HttpStatus.BAD_REQUEST);
            }
            User registeredUser = userService.register(UserMapper.asEntity(userDto));
            resultDto.setMessage("User registered");
            resultDto.setHttpStatus(HttpStatus.OK);
            resultDto.setResultObject(UserMapper.asDto(registeredUser));
            return new ResponseEntity<>(resultDto, HttpStatus.OK);
        } catch (AtosException e) {
            resultDto.setMessage(e.getMessage());
            resultDto.setHttpStatus(HttpStatus.FORBIDDEN);
            return new ResponseEntity<>(resultDto, HttpStatus.FORBIDDEN);
        }

    }

    @Override
    public ResponseEntity<ResultDto> displayUser(@PathVariable Integer id) {
        try {
            resultDto = new ResultDto();
            if (id == null || id <= 0) {
                resultDto.setMessage("User id is not valid");
                resultDto.setHttpStatus(HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(resultDto, HttpStatus.BAD_REQUEST);
            }
            resultDto.setMessage("User details");
            resultDto.setHttpStatus(HttpStatus.OK);
            resultDto.setResultObject(UserMapper.asDto(userService.display(id)));
            return new ResponseEntity<>(resultDto, HttpStatus.OK);
        } catch (AtosException e) {
            resultDto.setMessage(e.getMessage());
            resultDto.setHttpStatus(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(resultDto, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * check if mandatory data of user are set
     *
     * @param userDto data of user
     * @return true if mandatory data are set, false otherwise
     */
    private boolean isUserDataCorrect(UserDto userDto) {
        return userDto != null &&
                StringUtils.isNotBlank(userDto.getUsername()) &&
                userDto.getBirthdate() != null &&
                StringUtils.isNotBlank(userDto.getCountry());
    }

    /**
     * check if user is adult
     * @param userDto data of user
     * @return true if user id adult, false otherwise
     */
    private boolean isUserAgeCorrect(UserDto userDto){
        return DateHelper.computeYears(userDto.getBirthdate()) >= adultAge;
    }
}
