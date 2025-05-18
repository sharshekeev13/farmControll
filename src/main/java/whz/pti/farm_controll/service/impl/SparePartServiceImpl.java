package whz.pti.farm_controll.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whz.pti.farm_controll.dto.SparePartDto;
import whz.pti.farm_controll.entity.Equipment;
import whz.pti.farm_controll.entity.SparePart;
import whz.pti.farm_controll.repositories.EquipmentRepository;
import whz.pti.farm_controll.repositories.SparePartRepository;
import whz.pti.farm_controll.service.SparePartService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SparePartServiceImpl implements SparePartService {
    private final EquipmentRepository equipmentRepository;
    private final SparePartRepository sparePartRepository;

    private SparePart mapToEntity(SparePartDto dto) {
        SparePart sparePart = new SparePart();

        sparePart.setId(dto.getId());
        sparePart.setName(dto.getName());
        sparePart.setQuantity(dto.getQuantity());

        if (dto.getEquipmentIds() != null && !dto.getEquipmentIds().isEmpty()) {
            Set<Equipment> equipments = dto.getEquipmentIds().stream()
                    .map(id -> equipmentRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            sparePart.setEquipments(equipments);
        }

        return sparePart;
    }

    private SparePartDto mapToDto(SparePart sparePart) {
        SparePartDto dto = new SparePartDto();

        dto.setId(sparePart.getId());
        dto.setName(sparePart.getName());
        dto.setQuantity(sparePart.getQuantity());

        if (sparePart.getEquipments() != null && !sparePart.getEquipments().isEmpty()) {
            Set<Long> equipmentIds = sparePart.getEquipments().stream()
                    .map(Equipment::getId)
                    .collect(Collectors.toSet());

            List<String> equipmentNames = sparePart.getEquipments().stream()
                    .map(Equipment::getEquipmentName)
                    .collect(Collectors.toList());

            dto.setEquipmentIds(equipmentIds);
            dto.setEquipmentNames(equipmentNames);
        }

        return dto;
    }

    @Override
    public void saveSparePart(SparePartDto sparePartDto) {
        SparePart sparePart = mapToEntity(sparePartDto);
        SparePart savedSparePart = sparePartRepository.save(sparePart);
        mapToDto(savedSparePart);

    }

    @Override
    public void updateSparePart(SparePartDto sparePartDto) {
        if (sparePartDto.getId() == null || !sparePartRepository.existsById(sparePartDto.getId())) {
            throw new EntityNotFoundException("Spare part with id " + sparePartDto.getId() + " not found");
        }

        SparePart sparePart = mapToEntity(sparePartDto);
        SparePart updatedSparePart= sparePartRepository.save(sparePart);
        mapToDto(updatedSparePart);

    }

    @Override
    public List<SparePartDto> findAllSparePart() {
        List<SparePart> spareParts = sparePartRepository.findAll();
        return spareParts.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSparePart(Long id) {
        if (!sparePartRepository.existsById(id)) {
            throw new EntityNotFoundException("Spare part with id " + id + " not found");
        }
        sparePartRepository.deleteById(id);
    }

    public SparePartDto findSparePartById(Long id) {
        SparePart sparePart = sparePartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spare part with id " + id + " not found"));
        return mapToDto(sparePart);
    }
}
