package com.dsluchenko.app.url_shortener.mapper;

import com.dsluchenko.app.url_shortener.dto.UserDto;
import com.dsluchenko.app.url_shortener.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTest {
    @Test
    public void shouldMapUserFromUserDto() {
        UserMapper mapper = Mappers.getMapper(UserMapper.class);

        //given
        UserDto userDto = new UserDto("login", "password");

        //when
        User user = mapper.userFromUserDto(userDto);

        //then
        assertNotNull(user);
        assertEquals(user.getLogin(), userDto.getLogin());
        assertEquals(user.getPassword(), userDto.getPassword());
    }
}
