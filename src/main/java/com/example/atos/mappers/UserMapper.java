package com.example.atos.mappers;

import com.example.atos.dto.UserDto;
import com.example.atos.models.User;

public class UserMapper {

    /**
     * convert dto to entity
     * @param userDto to be converted
     * @return entity
     */
    public static User asEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setBirthdate(userDto.getBirthdate());
        user.setCountry(userDto.getCountry());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }

    /**
     * convert entity to dto
     * @param user entity to be converted
     * @return dto
     */
    public static UserDto asDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setBirthdate(user.getBirthdate());
        userDto.setCountry(user.getCountry());
        userDto.setGender(user.getGender());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }
}
