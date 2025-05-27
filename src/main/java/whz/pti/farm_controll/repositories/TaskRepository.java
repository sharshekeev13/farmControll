package whz.pti.farm_controll.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import whz.pti.farm_controll.dto.TaskDto;
import whz.pti.farm_controll.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import whz.pti.farm_controll.entity.Users;

import java.util.Collection;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

    Collection<Object> findByUsersEmail(String usersEmail);

    List<Task> findByUsers(Users users);

    @Query("SELECT t FROM Task t WHERE t.taskStatus ='ABGESCHLOSSEN'")
    List<Task> findCompletedTasks();

    @Query("SELECT t FROM Task t WHERE t.taskStatus = 'GEPLANT'")
    List<Task> findPlannedTasks();

    @Query("SELECT t FROM Task t WHERE t.taskStatus = 'IN_ARBEIT'")
    List<Task> findInProgressTasks();

    @Query("SELECT t FROM Task t WHERE t.taskStatus = 'VERSCHOBEN'")
    List<Task> findPostponedTasks();


}
