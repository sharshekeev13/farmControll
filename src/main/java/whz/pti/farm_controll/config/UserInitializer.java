package whz.pti.farm_controll.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import whz.pti.farm_controll.entity.User;
import whz.pti.farm_controll.enums.Role;
import whz.pti.farm_controll.repositories.UserRepository;


@Component
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByEmail("admin").isEmpty()) {
            User user = new User();
            user.setEmail("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setFirstName("Admin");
            user.setLastName("User");
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
    }
}
