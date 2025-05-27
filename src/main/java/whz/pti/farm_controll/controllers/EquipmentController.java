package whz.pti.farm_controll.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
        model.addAttribute("filter", "all");
        return "equipments";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public String showCreateForm(Model model) {
        model.addAttribute("equipment", new EquipmentDto());
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("spareParts", sparePartRepository.findAll());
        model.addAttribute("statuses", EquipmentStatus.values());
        return "create-equipment";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public String saveEquipment(@ModelAttribute("equipment") EquipmentDto dto) {
        equipmentService.saveEquipment(dto);
        return "redirect:/equipments";
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
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
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public String updateEquipment(@ModelAttribute("equipment") EquipmentDto dto) {
        equipmentService.updateEquipment(dto);
        return "redirect:/equipments";
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return "redirect:/equipments";
    }

    @GetMapping("/betriebsbereit")
    public String listBetriebsbereit(Model model) {
        model.addAttribute("equipments", equipmentService.findBetriebsbereitEquipment());
        model.addAttribute("filter", "betriebsbereit");
        return "equipments";
    }

    @GetMapping("/funktion")
    public String listFunktion(Model model) {
        model.addAttribute("equipments", equipmentService.findFunktionEquipment());
        model.addAttribute("filter", "funktion");
        return "equipments";
    }

    @GetMapping("/in-wartung")
    public String listInWartung(Model model) {
        model.addAttribute("equipments", equipmentService.findInWartungEquipment());
        model.addAttribute("filter", "in-wartung");
        return "equipments";
    }

    @GetMapping("/aussser-betriebsbereit")
    public String listAusserBetriebsbereit(Model model) {
        model.addAttribute("equipments", equipmentService.findAusserBetriebsbereitEquipment());
        model.addAttribute("filter", "aussser-betriebsbereit");
        return "equipments";
    }

    @GetMapping("/stillgelegt")
    public String listStillgelegt(Model model) {
        model.addAttribute("equipments", equipmentService.findStilllegTEquipment());
        model.addAttribute("filter", "stillgelegt");
        return "equipments";
    }
}
