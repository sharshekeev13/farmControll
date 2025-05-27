package whz.pti.farm_controll.service;

import whz.pti.farm_controll.dto.TaskDto;

import java.util.List;

public interface TaskService {
    void saveTask(TaskDto taskDto);
    void updateTask(TaskDto taskDto);
    List<TaskDto> findAllTask();
    void deleteTask(Long id);
    List<TaskDto> findAllTaskByEmail(String email);
    List<TaskDto> findPlannedTasks();
    List<TaskDto> findInProgressTasks();
    List<TaskDto> findCompletedTasks();
    List<TaskDto> findPostponedTasks();
}