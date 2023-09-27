package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService extends CrudService<TaskDTO,Long> {

    int findUnfinishedTaskCount(ProjectDTO project);
    int findCompletedTaskCount(ProjectDTO project);

    List<TaskDTO> findNotCompletedTasks();

    List<TaskDTO> findCompletedTasks();
    void taskStatusUpdate(TaskDTO task);

}
