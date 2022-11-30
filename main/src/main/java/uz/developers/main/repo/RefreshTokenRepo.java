package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.RefreshToken;

import java.util.Optional;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
