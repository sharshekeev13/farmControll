package whz.pti.farm_controll.service;

import whz.pti.farm_controll.dto.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDto equipmentDto);
    void updateEquipment(EquipmentDto equipmentDto);
    List<EquipmentDto> findAllEquipment();
    void deleteEquipment(Long id);
    EquipmentDto getEquipmentById(Long id);
    List<EquipmentDto> findBetriebsbereitEquipment();
    List<EquipmentDto> findFunktionEquipment();
    List<EquipmentDto> findInWartungEquipment();
    List<EquipmentDto> findAusserBetriebsbereitEquipment();
    List<EquipmentDto> findStilllegTEquipment();
}
