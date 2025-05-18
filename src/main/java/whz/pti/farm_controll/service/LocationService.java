package whz.pti.farm_controll.service;


import whz.pti.farm_controll.dto.LocationDto;

import java.util.List;

public interface LocationService {
    void saveLocation(LocationDto locationDto);
    void updateLocation(LocationDto locationDto);
    List<LocationDto> findAllLocations();
    void deleteLocation(Long id);
    LocationDto getLocationById(Long id);
}
