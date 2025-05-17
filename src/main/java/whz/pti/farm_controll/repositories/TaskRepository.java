package whz.pti.farm_controll.repositories;

import org.springframework.stereotype.Repository;
import whz.pti.farm_controll.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
