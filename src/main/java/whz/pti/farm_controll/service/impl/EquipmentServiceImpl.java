package whz.pti.farm_controll.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whz.pti.farm_controll.dto.EquipmentDto;
import whz.pti.farm_controll.entity.Equipment;
import whz.pti.farm_controll.entity.Location;
import whz.pti.farm_controll.entity.SparePart;
import whz.pti.farm_controll.entity.Task;
import whz.pti.farm_controll.repositories.EquipmentRepository;
import whz.pti.farm_controll.repositories.LocationRepository;
import whz.pti.farm_controll.repositories.SparePartRepository;
import whz.pti.farm_controll.repositories.TaskRepository;
import whz.pti.farm_controll.service.EquipmentService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final TaskRepository taskRepository;
    private final LocationRepository locationRepository;
    private final SparePartRepository sparePartRepository;

    private Equipment mapToEntity(EquipmentDto dto) {
        Equipment equipment = new Equipment();

        equipment.setId(dto.getId());
        equipment.setEquipmentName(dto.getEquipmentName());
        equipment.setModel(dto.getModel());
        equipment.setManufacturer(dto.getManufacturer());
        equipment.setInstallationDate(dto.getInstallationDate());
        equipment.setEquipmentStatus(dto.getEquipmentStatus());

        if (dto.getLocationId() != null) {
            Location location = locationRepository.findById(dto.getLocationId()).orElse(null);
            equipment.setLocation(location);
        }

        if (dto.getTaskId() != null && !dto.getTaskId().isEmpty()) {
            Set<Task> tasks = dto.getTaskId().stream()
                    .map(id -> taskRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            equipment.setTasks(tasks);
        }

        if (dto.getSparePartIds() != null && !dto.getSparePartIds().isEmpty()) {
            Set<SparePart> spareParts = dto.getSparePartIds().stream()
                    .map(id -> sparePartRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            equipment.setSpareParts(spareParts);
        }

        return equipment;
    }

    private EquipmentDto mapToDto(Equipment equipment) {
        EquipmentDto dto = new EquipmentDto();

        dto.setId(equipment.getId());
        dto.setEquipmentName(equipment.getEquipmentName());
        dto.setModel(equipment.getModel());
        dto.setManufacturer(equipment.getManufacturer());
        dto.setInstallationDate(equipment.getInstallationDate());
        dto.setEquipmentStatus(equipment.getEquipmentStatus());
        dto.setEquipmentStatusName(
                equipment.getEquipmentStatus() != null ? equipment.getEquipmentStatus().name() : ""
        );

        if (equipment.getLocation() != null) {
            dto.setLocationId(equipment.getLocation().getId());
            dto.setLocationName(equipment.getLocation().getName());
        }

        if (equipment.getTasks() != null && !equipment.getTasks().isEmpty()) {
            Set<Long> taskIds = equipment.getTasks().stream()
                    .map(Task::getId)
                    .collect(Collectors.toSet());
            List<String> taskNames = equipment.getTasks().stream()
                    .map(Task::getName)  // или getTaskName(), если у тебя другое название
                    .collect(Collectors.toList());

            dto.setTaskId(taskIds);
            dto.setTaskNames(taskNames);
        }

        if (equipment.getSpareParts() != null && !equipment.getSpareParts().isEmpty()) {
            Set<Long> sparePartIds = equipment.getSpareParts().stream()
                    .map(SparePart::getId)
                    .collect(Collectors.toSet());
            List<String> sparePartNames = equipment.getSpareParts().stream()
                    .map(SparePart::getName)
                    .collect(Collectors.toList());

            dto.setSparePartIds(sparePartIds);
            dto.setSparePartNames(sparePartNames);
        }

        return dto;
    }

    @Override
    public void saveEquipment(EquipmentDto equipmentDto) {
        System.out.println("equipmentStatus (DTO): " + equipmentDto.getEquipmentStatus());
        Equipment equipment = mapToEntity(equipmentDto);
        Equipment savedEquipment = equipmentRepository.save(equipment);
        mapToDto(savedEquipment);
    }

    @Override
    public void updateEquipment(EquipmentDto equipmentDto) {
        if (equipmentDto.getId() == null || !equipmentRepository.existsById(equipmentDto.getId())) {
            throw new EntityNotFoundException("Equipment with id " + equipmentDto.getId() + " not found");
        }

        Equipment equipment = mapToEntity(equipmentDto);

        Equipment updatedEquipment = equipmentRepository.save(equipment);
        mapToDto(updatedEquipment);
    }

    @Override
    public List<EquipmentDto> findAllEquipment() {
        List<Equipment> equipments = equipmentRepository.findAll();
        return equipments.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEquipment(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Equipment with id " + id + " not found");
        }
        equipmentRepository.deleteById(id);
    }
    @Override
    public EquipmentDto getEquipmentById(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Оборудование с id " + id + " не найдено"));
        return mapToDto(equipment);
    }


}
