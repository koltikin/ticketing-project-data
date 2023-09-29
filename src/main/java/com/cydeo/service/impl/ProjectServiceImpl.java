package com.cydeo.service.impl;

import com.cydeo.Repository.ProjectRepository;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper mapper;
    private final ProjectRepository repository;
    @Override
    public List<ProjectDTO> findAll() {
        return (repository.findAll(Sort.by("projectName"))).stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return mapper.convertToDto(repository.findByProjectCode(projectCode));
    }

    @Override
    public void save(ProjectDTO dto) {
        Project project = mapper.convertToEntity(dto);
        project.setProjectStatus(Status.OPEN);
        repository.save(project);

    }

    @Override
    public void update(ProjectDTO dto) {
        Long old_proj_id = repository.findByProjectCode(dto.getProjectCode()).getId();
        Project updtedProject = mapper.convertToEntity(dto);
        updtedProject.setId(old_proj_id);
        repository.save(updtedProject);


    }

    @Override
    public void delete(String projectCode) {
        Project delProject  = repository.findByProjectCode(projectCode);
                delProject.setIsDeleted(true);
                repository.save(delProject);

    }

    @Override
    public void projectComplete(String projectCode) {
        Project project = repository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        repository.save(project);
    }
}
