package com.cydeo.converter;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter implements Converter<String, UserDTO> {
    private final UserService userService;
    @Override
    public UserDTO convert(String userName) {
        return userService.findById(userName);
    }
}
