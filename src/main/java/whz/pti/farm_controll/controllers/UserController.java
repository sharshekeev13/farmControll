package whz.pti.farm_controll.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.pti.farm_controll.dto.UserDto;
import whz.pti.farm_controll.entity.Users;
import whz.pti.farm_controll.enums.Role;
import whz.pti.farm_controll.service.UserService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService userService;


    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "users";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", Role.values());
        return "create-user";
    }

    @GetMapping("/update/{id}")
    public String showEditForm(Model model, @PathVariable Long id) {
        UserDto userDto = userService.findById(id);
        model.addAttribute("user", userDto);
        model.addAttribute("roles", Role.values());
        return "create-user";
    }

    @PostMapping("/save")
    public String saveUser(UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(UserDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }



}
