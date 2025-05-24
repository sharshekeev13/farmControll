package whz.pti.farm_controll.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import whz.pti.farm_controll.dto.UserDto;
import whz.pti.farm_controll.entity.Users;
import whz.pti.farm_controll.enums.Role;
import whz.pti.farm_controll.repositories.UserRepository;
import whz.pti.farm_controll.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(UserDto UserDto) {
        Users user = new Users();
        user.setEmail(UserDto.getEmail());
        user.setPassword(UserDto.getPassword());
        user.setLastName(UserDto.getLastName());
        user.setFirstName(UserDto.getFirstName());
        user.setRole(Role.valueOf(UserDto.getRole()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto UserDto) {
        Users user = userRepository.findById(UserDto.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        if(UserDto.getEmail() != null) {
            user.setEmail(UserDto.getEmail());
        }
        if (UserDto.getPassword() != null) {
            user.setPassword(UserDto.getPassword());
        }
        if (UserDto.getLastName() != null) {
            user.setLastName(UserDto.getLastName());
        }
        if (UserDto.getFirstName() != null) {
            user.setFirstName(UserDto.getFirstName());
        }
        if (UserDto.getRole() != null) {
            user.setRole(Role.valueOf(UserDto.getRole()));
        }
        userRepository.save(user);
    }

    @Override
    public List<Users> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public UserDto findById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setLastName(user.getLastName());
        dto.setFirstName(user.getFirstName());
        dto.setRole(user.getRole().name());
        return dto;
    }
}
