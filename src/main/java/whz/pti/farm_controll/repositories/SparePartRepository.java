package whz.pti.farm_controll.repositories;

import whz.pti.farm_controll.entity.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Long> {
}
