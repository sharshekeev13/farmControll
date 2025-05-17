package whz.pti.farm_controll.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.farm_controll.dto.EquipmentDto;
import whz.pti.farm_controll.enums.EquipmentStatus;
import whz.pti.farm_controll.repositories.LocationRepository;
import whz.pti.farm_controll.repositories.SparePartRepository;
import whz.pti.farm_controll.repositories.TaskRepository;
import whz.pti.farm_controll.service.impl.EquipmentServiceImpl;

@Controller
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentServiceImpl equipmentService;
    private final LocationRepository locationRepository;
    private final TaskRepository taskRepository;
    private final SparePartRepository sparePartRepository;

    @GetMapping
    public String listEquipments(Model model) {
        model.addAttribute("equipments", equipmentService.findAllEquipment());
        return "equipments";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("equipment", new EquipmentDto());
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("spareParts", sparePartRepository.findAll());
        model.addAttribute("statuses", EquipmentStatus.values());
        return "create-equipment";
    }

    @PostMapping("/save")
    public String saveEquipment(@ModelAttribute("equipment") EquipmentDto dto) {
        equipmentService.saveEquipment(dto);
        return "redirect:/equipments";
    }

    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        EquipmentDto dto = equipmentService.getEquipmentById(id);
        model.addAttribute("equipment", dto);
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("spareParts", sparePartRepository.findAll());
        model.addAttribute("statuses", EquipmentStatus.values());
        return "create-equipment";
    }

    @PostMapping("/update")
    public String updateEquipment(@ModelAttribute("equipment") EquipmentDto dto) {
        equipmentService.updateEquipment(dto);
        return "redirect:/equipments";
    }


    @GetMapping("/delete/{id}")
    public String deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return "redirect:/equipments";
    }
}
