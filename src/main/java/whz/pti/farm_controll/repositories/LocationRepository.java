package whz.pti.farm_controll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import whz.pti.farm_controll.entity.Location;

import java.util.List;
import java.util.Map;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("SELECT l.name AS locationName, COUNT(e.id) AS equipmentCount " +
            "FROM Location l LEFT JOIN Equipment e ON e.location.id = l.id " +
            "GROUP BY l.name")
    List<Map<String, Object>> countEquipmentsByLocation();
}
