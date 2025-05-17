package whz.pti.farm_controll.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.farm_controll.dto.TaskDto;
import whz.pti.farm_controll.enums.TaskStatus;
import whz.pti.farm_controll.repositories.EquipmentRepository;
import whz.pti.farm_controll.repositories.LocationRepository;
import whz.pti.farm_controll.repositories.UserRepository;
import whz.pti.farm_controll.service.TaskService;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTask());
        return "tasks"; // task/list.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new TaskDto());
        model.addAttribute("equipments", equipmentRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("statuses", TaskStatus.values());
        return "create-task"; // task/form.html
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") TaskDto dto) {
        taskService.saveTask(dto);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TaskDto dto = taskService.findAllTask().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));

        model.addAttribute("task", dto);
        model.addAttribute("equipments", equipmentRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("statuses", TaskStatus.values());
        return "create-task";
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute("task") TaskDto dto) {
        taskService.updateTask(dto);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}

