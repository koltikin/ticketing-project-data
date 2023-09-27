package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    @Override
    public ProjectDTO save(ProjectDTO project) {
        if(project.getProjectStatus()==null) project.setProjectStatus(Status.OPEN);
        return super.save(project,project.getProjectCode());
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);

    }

    @Override
    public void update(ProjectDTO project) {

        super.update(project,project.getProjectCode());

    }

    @Override
    public void projectComplete(String projectCode) {
        findById(projectCode).setProjectStatus(Status.COMPLETE);
    }

    @Override
    public List<ProjectDTO> findCountedProjectsByManager(UserDTO manager) {
        return findAll().stream().filter(prj->prj.getProjectManager().equals(manager))
                .map(prj->{

                    int unFinishedCount = taskService.findUnfinishedTaskCount(prj);
                    int completedTaskCount = taskService.findCompletedTaskCount(prj);


                    prj.setUnfinishedCount(unFinishedCount);
                    prj.setCompletedCount(completedTaskCount);

                    return prj;


                }).collect(Collectors.toList());
    }
}
