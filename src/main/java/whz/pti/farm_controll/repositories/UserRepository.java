package whz.pti.farm_controll.repositories;

import org.springframework.stereotype.Repository;
import whz.pti.farm_controll.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

}
