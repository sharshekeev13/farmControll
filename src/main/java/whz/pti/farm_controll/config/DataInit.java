package whz.pti.farm_controll.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.javafaker.Faker;

import whz.pti.farm_controll.entity.*;
import whz.pti.farm_controll.enums.EquipmentStatus;
import whz.pti.farm_controll.enums.Role;
import whz.pti.farm_controll.enums.TaskStatus;
import whz.pti.farm_controll.repositories.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final EquipmentRepository equipmentRepository;
    private final SparePartRepository sparePartRepository;
    private final LocationRepository locationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker(Locale.forLanguageTag("de"));

        if (userRepository.count() == 0) {
            Users admin = new Users();
            admin.setEmail("admin@farm.de");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setRole(Role.ADMIN);

            Users manager = new Users();
            manager.setEmail("manager@farm.de");
            manager.setPassword(passwordEncoder.encode("manager123"));
            manager.setFirstName("Manager");
            manager.setLastName("Worker");
            manager.setRole(Role.MANAGER);

            Users arbeiter = new Users();
            arbeiter.setEmail("arbeiter@farm.de");
            arbeiter.setPassword(passwordEncoder.encode("arbeiter123"));
            arbeiter.setFirstName("Adam");
            arbeiter.setLastName("Wolf");
            arbeiter.setRole(Role.USER);

            List<Users> moreUsers = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {
                Users u = new Users();
                u.setEmail("user" + i + "@farm.de");
                u.setPassword(passwordEncoder.encode("user" + i + "pass"));
                u.setFirstName(faker.name().firstName());
                u.setLastName(faker.name().lastName());
                u.setRole(Role.USER);
                moreUsers.add(u);
            }

            userRepository.saveAll(new ArrayList<>(List.of(admin, manager, arbeiter)));
            userRepository.saveAll(moreUsers);

        }

        if (locationRepository.count() == 0) {
            List<Location> locations = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Location loc = new Location();
                loc.setName("Stall " + faker.number().digit());
                loc.setDescription(faker.lorem().sentence());
                loc.setAddress(faker.address().fullAddress());
                loc.setCoordinates(faker.address().latitude() + "," + faker.address().longitude());
                locations.add(loc);
            }
            locationRepository.saveAll(locations);
        }

        if (sparePartRepository.count() == 0) {
            List<SparePart> parts = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                parts.add(new SparePart(null, faker.commerce().productName(), faker.number().numberBetween(1L, 100L)));
            }
            sparePartRepository.saveAll(parts);
        }

        if (equipmentRepository.count() == 0) {
            List<Location> locations = locationRepository.findAll();
            List<Equipment> equipments = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                Equipment eq = new Equipment();
                eq.setEquipmentName(faker.app().name());
                eq.setModel(faker.bothify("EQ-###"));
                eq.setManufacturer(faker.company().name());
                eq.setInstallationDate(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 365)));
                eq.setEquipmentStatus(
                        EquipmentStatus.values()[faker.random().nextInt(EquipmentStatus.values().length)]);
                eq.setLocation(faker.options().nextElement(locations));
                equipments.add(eq);
            }

            equipmentRepository.saveAll(equipments);
        }

        if (taskRepository.count() == 0) {
            List<Users> users = userRepository.findAll().stream()
                    .filter(u -> u.getRole() == Role.USER)
                    .toList();

            var equipments = equipmentRepository.findAll();
            var locations = locationRepository.findAll();
            List<Task> tasks = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                Task task = new Task();
                task.setName(faker.job().title());
                task.setDescription(faker.lorem().sentence());
                LocalDateTime start = LocalDateTime.now().plusDays(faker.number().numberBetween(-30, 30));
                task.setStartTime(start);
                task.setEndTime(start.plusHours(1));
                task.setTaskStatus(TaskStatus.values()[faker.random().nextInt(TaskStatus.values().length)]);
                task.setUsers(faker.options().nextElement(users));
                task.setLocation(faker.options().nextElement(locations));
                task.setEquipments(Set.of(faker.options().nextElement(equipments)));
                tasks.add(task);
            }

            taskRepository.saveAll(tasks);
        }
    }
}
