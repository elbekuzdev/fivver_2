package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.main.entity.Users;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmailAndIsActive(String email,Boolean isactive);
}
