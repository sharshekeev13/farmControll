package whz.pti.farm_controll.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.farm_controll.dto.SparePartDto;
import whz.pti.farm_controll.repositories.EquipmentRepository;
import whz.pti.farm_controll.repositories.SparePartRepository;
import whz.pti.farm_controll.service.SparePartService;

@Controller
@RequestMapping("/spare-parts")
@RequiredArgsConstructor
public class SparePartController {
    private final SparePartService sparePartService;
    private final EquipmentRepository equipmentRepository;

    @GetMapping
    public String listSpareParts(Model model) {
        model.addAttribute("spareParts", sparePartService.findAllSparePart());
        return "spare-parts";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("sparePart", new SparePartDto());
        model.addAttribute("equipments", equipmentRepository.findAll());
        return "create-spare-part";
    }

    @PostMapping("/save")
    public String saveSparePart(@ModelAttribute("sparePart") SparePartDto dto) {
        sparePartService.saveSparePart(dto);
        return "redirect:/spare-parts";
    }

    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        SparePartDto dto = sparePartService.findSparePartById(id);
        model.addAttribute("sparePart", dto);
        model.addAttribute("equipments", equipmentRepository.findAll());
        return "create-spare-part";
    }
    @PostMapping("/update")
    public String updateSparePart(@ModelAttribute("sparePart") SparePartDto dto) {
        sparePartService.updateSparePart(dto);
        return "redirect:/spare-parts";
    }

    @GetMapping("/delete/{id}")
    public String deleteSparePart(@PathVariable Long id) {
        sparePartService.deleteSparePart(id);
        return "redirect:/spare-parts";
    }


}
