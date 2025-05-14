package whz.pti.farm_controll.repositories;

import whz.pti.farm_controll.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
}
