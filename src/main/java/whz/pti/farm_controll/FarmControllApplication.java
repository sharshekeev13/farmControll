package whz.pti.farm_controll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FarmControllApplication {

    public static void main(String[] args) {
        var encoder = new BCryptPasswordEncoder();
        SpringApplication.run(FarmControllApplication.class, args);
    }



}
