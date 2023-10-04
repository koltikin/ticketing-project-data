package com.cydeo.service.impl;

import com.cydeo.Repository.UserRepository;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository repository;
    private final ProjectService projectService;
    private final TaskService taskService;

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
        Long old_user_id = repository.findByUserName(dto.getUserName()).getId();
        User new_user = mapper.convertToEntity(dto);
        new_user.setId(old_user_id);
        repository.save(new_user);

    }

    @Override
    public void delete(String username) {
        User user = repository.findByUserName(username);
        if (checkIfUserCanBeDeleted(user)) {
            user.setIsDeleted(true);
            repository.save(user);
        }

    }

    @Override
    public List<UserDTO> findAllByRole(String description) {
        return repository.findByRole_DescriptionIgnoreCase(description)
                .stream().map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    private boolean checkIfUserCanBeDeleted(User user){
        switch (user.getRole().getDescription()){
            case "Manager":
                List<ProjectDTO> notCompletedProjects = projectService.listAllNotCompletedPrjByManager(user);
                return notCompletedProjects.size() == 0;
            case "employee":
                List<TaskDTO> notCompletedTasks = taskService.listAllNotCompletedPrjByEmployee(user);
                return notCompletedTasks.size() == 0;

            default: return true;
        }
    }
}
