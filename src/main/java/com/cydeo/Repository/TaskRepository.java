package com.cydeo.Repository;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND " +
            "t.taskStatus = 'COMPLETED'")
    int totalCompletedTaskCount(String projectCode);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM tasks JOIN projects p" +
            " ON tasks.project_id = p.id WHERE p.project_code = ?1" +
            " AND tasks.task_status <> 'COMPLETED'")
    int totalUnfinishedTaskCount(String projectCode);


    @Query("SELECT t FROM Task t WHERE t.taskStatus != 'COMPLETE'")
    List<Task> findAllNotCompletedTask();

    @Query("SELECT t FROM Task t WHERE t.taskStatus = 'COMPLETE'")
    List<Task> findAllCompletedTask();

    List<Task> findAllByTaskStatusIsNotAndAssignedEmployee(Status status, User user);
    List<Task> findAllByTaskStatusIsAndAssignedEmployee(Status status, User user);

    List<Task> findAllByProject(Project project);
}
