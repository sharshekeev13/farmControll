package whz.pti.farm_controll.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whz.pti.farm_controll.dto.TaskDto;
import whz.pti.farm_controll.entity.Equipment;
import whz.pti.farm_controll.entity.Location;
import whz.pti.farm_controll.entity.Task;
import whz.pti.farm_controll.entity.Users;
import whz.pti.farm_controll.repositories.EquipmentRepository;
import whz.pti.farm_controll.repositories.LocationRepository;
import whz.pti.farm_controll.repositories.TaskRepository;
import whz.pti.farm_controll.repositories.UserRepository;
import whz.pti.farm_controll.service.TaskService;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    private Task mapToEntity(TaskDto dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStartTime(dto.getStartTime());
        task.setEndTime(dto.getEndTime());
        task.setTaskStatus(dto.getTaskStatus());
        if (dto.getUserId() != null) {
            Users user = userRepository.findById(dto.getUserId()).orElse(null);
            task.setUsers(user);
        }
        if (dto.getLocationId() != null) {
            Location location = locationRepository.findById(dto.getLocationId()).orElse(null);
            task.setLocation(location);
        }
        if (dto.getEquipmentIds() != null && !dto.getEquipmentIds().isEmpty()) {
            Set<Equipment> equipmentSet = dto.getEquipmentIds().stream()
                    .map(id -> equipmentRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            task.setEquipments(equipmentSet);
        }


        return task;
    }

    private TaskDto mapToDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setStartTime(task.getStartTime());
        dto.setEndTime(task.getEndTime());
        dto.setTaskStatus(task.getTaskStatus());
        dto.setTaskStatusName(task.getTaskStatus() != null ? task.getTaskStatus().name() : "");
        if (task.getUsers() != null) {
            dto.setUserId(task.getUsers().getId());
            dto.setUserName(task.getUsers().getUsername());
        }
        if (task.getLocation() != null) {
            dto.setLocationId(task.getLocation().getId());
            dto.setLocationName(task.getLocation().getName());
        }
        if (task.getEquipments() != null && !task.getEquipments().isEmpty()) {
            Set<Long> equipmentIds = new HashSet<>(task.getEquipments()).stream()
                    .map(Equipment::getId)
                    .collect(Collectors.toSet());
            dto.setEquipmentIds(equipmentIds);
        }

        return dto;
    }

    @Override
    public void saveTask(TaskDto dto) {
        Task task = mapToEntity(dto);
        Task saved = taskRepository.save(task);
        mapToDto(saved);
    }

    @Override
    public void updateTask(TaskDto dto) {
        Task task = mapToEntity(dto);
        Task saved = taskRepository.save(task);
        mapToDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> findAllTask() {
        return taskRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
