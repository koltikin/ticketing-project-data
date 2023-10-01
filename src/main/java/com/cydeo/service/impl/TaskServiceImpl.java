package com.cydeo.service.impl;

import com.cydeo.Repository.TaskRepository;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskMapper mapper;
    private final TaskRepository repository;
    @Override
    public List<TaskDTO> findAll() {
        return repository.findAll(Sort.by("assignedDate")).stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()){
            return mapper.convertToDto(task.get());
        }
        return null;
    }

    @Override
    public void save(TaskDTO dto) {
        repository.save(mapper.convertToEntity(dto));

    }

    @Override
    public void update(TaskDTO dto) {
        repository.save(mapper.convertToEntity(dto));

    }

    @Override
    public void delete(Long id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            task.get().setIsDeleted(true);
            repository.save(task.get());
        }

    }

    @Override
    public int getAllCompletedTaskCount(String projectCode) {
        return repository.totalCompletedTaskCount(projectCode);
    }

    @Override
    public int getAllUnfinishedTaskCount(String projectCode) {
        return repository.totalUnfinishedTaskCount(projectCode);
    }

    @Override
    public List<TaskDTO> getAllTasksNotCompleted() {
        return repository.findAllNotCompletedTask().stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getAllCompletedTasks() {
        return repository.findAllCompletedTask().stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());

    }


}
