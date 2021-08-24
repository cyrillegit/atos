package com.example.atos.mappers;

import com.example.atos.dto.UserDto;
import com.example.atos.models.User;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Selma;

@Mapper(withIgnoreMissing = IgnoreMissing.ALL)
public interface GenericMapper {

    /**
     * Instance of the mapper
     */
    GenericMapper INSTANCE = Selma.getMapper(GenericMapper.class);

    /**
     * convert dto to entity
     * @param userDto to be converted
     * @return entity
     */
    User asEntity(UserDto userDto);

    /**
     * convert entity to dto
     * @param user entity to be converted
     * @return dto
     */
    UserDto asDto(User user);

}
