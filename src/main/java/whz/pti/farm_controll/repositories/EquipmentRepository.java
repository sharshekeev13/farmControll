package whz.pti.farm_controll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.pti.farm_controll.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
