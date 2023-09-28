package com.cydeo.mapper;


import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<UserDTO,User> {
    public UserMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }
    @Override
    protected Class<UserDTO> getDtoClass() {
        return UserDTO.class;
    }
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
