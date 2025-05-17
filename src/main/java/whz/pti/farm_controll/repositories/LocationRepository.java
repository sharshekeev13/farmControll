package whz.pti.farm_controll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whz.pti.farm_controll.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
