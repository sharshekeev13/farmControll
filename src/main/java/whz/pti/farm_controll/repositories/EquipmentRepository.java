package whz.pti.farm_controll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import whz.pti.farm_controll.entity.Equipment;


import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    @Query("SELECT e FROM Equipment e WHERE e.equipmentStatus  ='BETRIEBSBEREIT'")
    List<Equipment> findBetriebsbereitEquipment();

    @Query("SELECT e FROM Equipment e WHERE e.equipmentStatus='FUNKTIONSFÄHIG'")
    List<Equipment> findFunktionEquipment();

    @Query("SELECT e FROM Equipment e WHERE e.equipmentStatus='IN_WARTUNG'")
    List<Equipment> findInWartungEquipment();

    @Query("SELECT e FROM Equipment e WHERE e.equipmentStatus='AUSSER_BETRIEB'")
    List<Equipment> findAusserBetriebsbereitEquipment();

    @Query("SELECT  e FROM Equipment e WHERE e.equipmentStatus='STILLGELEGT'")
    List<Equipment> findStilllegTEquipment();

}

