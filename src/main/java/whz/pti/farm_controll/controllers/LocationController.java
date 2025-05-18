package whz.pti.farm_controll.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.farm_controll.dto.LocationDto;
import whz.pti.farm_controll.service.LocationService;

@Controller
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {
        private final LocationService locationService;

        @GetMapping
        public String listLocations(Model model) {
            model.addAttribute("locations", locationService.findAllLocations());
            return "locations";
        }

        @GetMapping("/new")
        public String showCreateForm(Model model) {
            model.addAttribute("location", new LocationDto());
            return "create-location";
        }

        @PostMapping("/save")
        public String saveLocation(@ModelAttribute("location") LocationDto dto) {
            locationService.saveLocation(dto);
            return "redirect:/locations";
        }

        @GetMapping("/update/{id}")
        public String showEditForm(@PathVariable Long id, Model model) {
            LocationDto dto = locationService.getLocationById(id);
            model.addAttribute("location", dto);
            return "create-location";
        }

        @PostMapping("/update")
        public String updateLocation(@ModelAttribute("location") LocationDto dto) {
            locationService.updateLocation(dto);
            return "redirect:/locations";
        }

        @GetMapping("/delete/{id}")
        public String deleteLocation(@PathVariable Long id) {
            locationService.deleteLocation(id);
            return "redirect:/locations";
        }
}

