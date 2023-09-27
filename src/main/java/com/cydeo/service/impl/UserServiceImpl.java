package com.cydeo.service.impl;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractMapService<UserDTO,String> implements UserService {
    @Override
    public UserDTO save(UserDTO user) {
        return super.save(user,user.getUserName());
    }
    @Override
    public UserDTO findById(String userName) {
        return super.findById(userName);
    }
    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }
    @Override
    public void deleteById(String userName) {
        super.deleteById(userName);
    }

    @Override
    public void update(UserDTO user) {
        super.update(user,user.getUserName());
    }

    @Override
    public List<UserDTO> findManagers() {
        return findAll().stream()
                .filter(user->user.getRole().getDescription().equals("Manager"))
                .collect(Collectors.toList());
    }
}
