package whz.pti.farm_controll.service;

import whz.pti.farm_controll.dto.UserDto;
import whz.pti.farm_controll.entity.Users;

import java.util.List;

public interface UserService {

    void saveUser(UserDto UserDto);
    void updateUser(UserDto UserDto);
    List<Users> findAllUser();
    void deleteUser(Long id);
    UserDto findById(Long id);
    
}
