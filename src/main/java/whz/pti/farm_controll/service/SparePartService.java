package whz.pti.farm_controll.service;

import whz.pti.farm_controll.dto.SparePartDto;


import java.util.List;

public interface SparePartService {
    void saveSparePart(SparePartDto sparePartDto);
    void updateSparePart(SparePartDto sparePartDto);
    List<SparePartDto> findAllSparePart();
    void deleteSparePart(Long id);
    SparePartDto findSparePartById(Long id);
}
