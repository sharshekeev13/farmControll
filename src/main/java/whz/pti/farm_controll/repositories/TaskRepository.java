package whz.pti.farm_controll.repositories;

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
}
