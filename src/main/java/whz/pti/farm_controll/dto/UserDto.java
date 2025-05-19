package whz.pti.farm_controll.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {
    Long id;
    String email;
    String password;
    String lastName;
    String firstName;
    String role;
}
