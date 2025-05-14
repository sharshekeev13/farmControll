package whz.pti.farm_controll.repositories;

import whz.pti.farm_controll.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>{
}
