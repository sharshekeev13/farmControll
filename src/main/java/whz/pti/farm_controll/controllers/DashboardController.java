package whz.pti.farm_controll.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import whz.pti.farm_controll.service.SparePartService;
import whz.pti.farm_controll.service.TaskService;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final TaskService taskService;
    private final SparePartService sparePartService;


    @GetMapping("/")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("tasks", taskService.findAllTask().size());
        model.addAttribute("myTasks", taskService.findAllTaskByEmail(username).size());
        model.addAttribute("spareParts", sparePartService.findAllSparePart().size());
        return "dashboard";
    }
}
