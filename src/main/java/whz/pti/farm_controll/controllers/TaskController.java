package whz.pti.farm_controll.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.farm_controll.dto.TaskDto;
import whz.pti.farm_controll.enums.TaskStatus;
import whz.pti.farm_controll.repositories.EquipmentRepository;
import whz.pti.farm_controll.repositories.LocationRepository;
import whz.pti.farm_controll.repositories.TaskRepository;
import whz.pti.farm_controll.repositories.UserRepository;
import whz.pti.farm_controll.service.TaskService;

@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final TaskRepository taskRepository;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTask());
        model.addAttribute("filter", "all");
        return "tasks";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new TaskDto());
        model.addAttribute("equipments", equipmentRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("statuses", TaskStatus.values());
        return "create-task";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public String saveTask(@ModelAttribute("task") TaskDto dto) {
        taskService.saveTask(dto);
        return "redirect:/tasks";
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public String showEditForm(@PathVariable Long id, Model model) {
        TaskDto dto = taskService.findAllTask().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Task not found"));

        model.addAttribute("task", dto);
        model.addAttribute("equipments", equipmentRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("statuses", TaskStatus.values());
        return "create-task";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public String updateTask(@ModelAttribute("task") TaskDto dto) {
        taskService.updateTask(dto);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/geplant")
    public String listPlannedTasks(Model model) {
        model.addAttribute("tasks", taskService.findPlannedTasks());
        model.addAttribute("filter", "geplant");
        return "tasks";
    }

    @GetMapping("/in-arbeit")
    public String listInProgressTasks(Model model) {
        model.addAttribute("tasks", taskService.findInProgressTasks());
        model.addAttribute("filter", "in-arbeit");
        return "tasks";
    }

    @GetMapping("/abgeschlossen")
    public String listCompletedTasks(Model model) {
        model.addAttribute("tasks", taskService.findCompletedTasks());
        model.addAttribute("filter", "abgeschlossen");
        return "tasks";
    }

    @GetMapping("/verschoben")
    public String listPostponedTasks(Model model) {
        model.addAttribute("tasks", taskService.findPostponedTasks());
        model.addAttribute("filter", "verschoben");
        return "tasks";
    }
}

