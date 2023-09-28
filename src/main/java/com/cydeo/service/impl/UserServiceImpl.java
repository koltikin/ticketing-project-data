package com.cydeo.service.impl;

import com.cydeo.Repository.UserRepository;
import com.cydeo.dto.UserDTO;
import com.cydeo.mapper.UserMapper;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository repository;

    @Override
    public List<UserDTO> findAll() {
        return repository.findAll(Sort.by("firstName")).stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(String userName) {
       return mapper.convertToDto(repository.findByUserName(userName));
    }
    @Override
    public void save(UserDTO dto) {
        repository.save(mapper.convertToEntity(dto));

    }

    @Override
    public void update(UserDTO dto) {
        repository.save(mapper.convertToEntity(dto));

    }

    @Override
    public void delete(UserDTO dto) {
        repository.delete(mapper.convertToEntity(dto));

    }
}
