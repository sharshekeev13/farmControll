package whz.pti.farm_controll.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whz.pti.farm_controll.dto.LocationDto;
import whz.pti.farm_controll.entity.Equipment;
import whz.pti.farm_controll.entity.Location;
import whz.pti.farm_controll.entity.Task;
import whz.pti.farm_controll.repositories.EquipmentRepository;
import whz.pti.farm_controll.repositories.LocationRepository;
import whz.pti.farm_controll.repositories.TaskRepository;
import whz.pti.farm_controll.service.LocationService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final EquipmentRepository equipmentRepository;
    private final TaskRepository taskRepository;

    private Location mapToEntity(LocationDto dto) {
        Location location = new Location();

        location.setId(dto.getId());
        location.setName(dto.getName());
        location.setDescription(dto.getDescription());
        location.setAddress(dto.getAddress());
        location.setCoordinates(dto.getCoordinates());

        if (dto.getEquipmentIds() != null && !dto.getEquipmentIds().isEmpty()) {
            List<Equipment> equipments = dto.getEquipmentIds().stream()
                    .map(id -> equipmentRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            location.setEquipments(equipments);
        }

        if (dto.getTaskIds() != null && !dto.getTaskIds().isEmpty()) {
            List<Task> tasks = dto.getTaskIds().stream()
                    .map(id -> taskRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            location.setTasks(tasks);
        }

        return location;
    }

    private LocationDto mapToDto(Location location) {
        LocationDto dto = new LocationDto();

        dto.setId(location.getId());
        dto.setName(location.getName());
        dto.setDescription(location.getDescription());
        dto.setAddress(location.getAddress());
        dto.setCoordinates(location.getCoordinates());

        if (location.getEquipments() != null && !location.getEquipments().isEmpty()) {
            List<Long> equipmentIds = location.getEquipments().stream()
                    .map(Equipment::getId)
                    .collect(Collectors.toList());
            List<String> equipmentNames = location.getEquipments().stream()
                    .map(Equipment::getEquipmentName)
                    .collect(Collectors.toList());

            dto.setEquipmentIds(equipmentIds);
            dto.setEquipmentNames(equipmentNames);
        }

        if (location.getTasks() != null && !location.getTasks().isEmpty()) {
            List<Long> taskIds = location.getTasks().stream()
                    .map(Task::getId)
                    .collect(Collectors.toList());
            List<String> taskNames = location.getTasks().stream()
                    .map(Task::getName)
                    .collect(Collectors.toList());

            dto.setTaskIds(taskIds);
            dto.setTaskNames(taskNames);
        }

        return dto;
    }


    @Override
    public void saveLocation(LocationDto locationDto) {
        Location location = mapToEntity(locationDto);
        Location savedLocation = locationRepository.save(location);
        mapToDto(savedLocation);
    }

    @Override
    public void updateLocation(LocationDto locationDto) {
        if (locationDto.getId() == null || !locationRepository.existsById(locationDto.getId())) {
            throw new EntityNotFoundException("Location with id " + locationDto.getId() + " not found");
        }

        Location location = mapToEntity(locationDto);
        Location updatedLocation = locationRepository.save(location);
        mapToDto(updatedLocation);
    }

    @Override
    public List<LocationDto> findAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new EntityNotFoundException("Location with id " + id + " not found");
        }
        locationRepository.deleteById(id);
    }

    @Override
    public LocationDto getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Локация с id " + id + " не найдена"));
        return mapToDto(location);
    }

}
