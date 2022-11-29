package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.auth.entity.Users;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByIdAndIsActive(Integer id, Boolean bool);
    Optional<Users> findByEmailAndIsActive(String email, Boolean bool);
    Boolean existsByEmailAndIsActive(String email, Boolean isActive);
    Boolean existsByPhoneNumberAndIsActive(String phoneNumber, Boolean isActive);

}
