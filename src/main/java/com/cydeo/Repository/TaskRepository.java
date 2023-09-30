package com.cydeo.Repository;

import com.cydeo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND " +
            "t.taskStatus = 'COMPLETED'")
    int totalCompletedTaskCount(String projectCode);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM tasks JOIN projects p" +
            " ON tasks.project_id = p.id WHERE p.project_code = ?1" +
            " AND tasks.task_status <> 'COMPLETED'")
    int totalUnfinishedTaskCount(String projectCode);

}
