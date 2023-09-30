package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO,String>{
    void projectComplete(String projectCode);
    List<ProjectDTO> findByProjectDetail();
}
