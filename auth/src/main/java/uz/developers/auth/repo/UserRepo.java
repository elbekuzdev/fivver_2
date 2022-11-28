package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.auth.entity.Users;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByIdAndIsActive(Integer id, Boolean bool);
}
