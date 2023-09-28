package com.cydeo.service.impl;

import com.cydeo.Repository.RoleRepository;
import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final RoleMapper roleMapper;
    @Override
    public List<RoleDTO> findAll() {
        List<Role> roleList = repository.findAll();

        return roleList.stream()
                .map(roleMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        return roleMapper.convertToDto(repository.findById(id).get());

    }

    @Override
    public void save(RoleDTO dto) {

    }

    @Override
    public void update(RoleDTO dto) {

    }

    @Override
    public void delete(RoleDTO dto) {

    }
}
