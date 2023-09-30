package com.cydeo.Repository;

import com.cydeo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {



    int totalCompletedTaskCount(String projectCode);

    int totalUnfinishedTaskCount(String projectCode);

}
