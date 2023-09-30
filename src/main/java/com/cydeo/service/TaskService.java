package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;

public interface TaskService extends CrudService<TaskDTO, Long>{

    int getAllCompletedTaskCount(String projectCode);
    int getAllUnfinishedTaskCount(String projectCode);
}
