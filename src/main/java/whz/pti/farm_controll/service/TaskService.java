package whz.pti.farm_controll.service;

import whz.pti.farm_controll.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto saveTask(TaskDto taskDto);
    TaskDto updateTask(TaskDto taskDto);
    List<TaskDto> findAllTask();
    void deleteTask(Long id);
}