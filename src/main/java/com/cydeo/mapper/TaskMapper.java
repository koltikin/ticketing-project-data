package com.cydeo.mapper;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper extends BaseMapper<TaskDTO, Task>{
    public TaskMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected Class<TaskDTO> getDtoClass() {
        return TaskDTO.class;
    }

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }
}
