package whz.pti.farm_controll.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import whz.pti.farm_controll.entity.*;
import whz.pti.farm_controll.enums.EquipmentStatus;
import whz.pti.farm_controll.enums.Role;
import whz.pti.farm_controll.enums.TaskStatus;
import whz.pti.farm_controll.repositories.*;

import java.time.LocalDateTime;
import java.util.List;
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


            userRepository.saveAll(List.of(admin, manager, arbeiter));
        }

        if (locationRepository.count() == 0) {
            Location stall = new Location();
            stall.setName("Hühnerstall 1");
            stall.setDescription("Hauptstall für Legehennen");
            stall.setAddress("Am Feldweg 12, 12345 Hühnerstadt");
            stall.setCoordinates("50.1109,8.6821");
            locationRepository.save(stall);
        }

        if (sparePartRepository.count() == 0) {
            sparePartRepository.saveAll(List.of(
                    new SparePart(null, "Futterdosierer", 20L),
                    new SparePart(null, "Wasserpumpe", 15L),
                    new SparePart(null, "Lüftungsfilter", 30L)
            ));
        }

        if (equipmentRepository.count() == 0) {
            Location loc = locationRepository.findAll().getFirst();

            Equipment equip = new Equipment();
            equip.setEquipmentName("Fütterungsautomat");
            equip.setModel("FA-200");
            equip.setManufacturer("AgroTech GmbH");
            equip.setInstallationDate(LocalDateTime.of(2023, 3, 15, 8, 0));
            equip.setEquipmentStatus(EquipmentStatus.BETRIEBSBEREIT);
            equip.setLocation(loc);

            equipmentRepository.save(equip);
        }

        if (taskRepository.count() == 0) {
            Task task = new Task();
            task.setName("Routinewartung");
            task.setDescription("Wöchentliche Kontrolle des Fütterungsautomaten");
            task.setStartTime(LocalDateTime.of(2024, 6, 1, 7, 0));
            task.setEndTime(LocalDateTime.of(2024, 6, 1, 8, 0));
            task.setTaskStatus(TaskStatus.GEPLANT);
            task.setUsers(userRepository.findAll().getFirst());
            task.setLocation(locationRepository.findAll().getFirst());
            task.setEquipments(Set.of(equipmentRepository.findAll().getFirst()));

            taskRepository.save(task);
        }
    }
}
