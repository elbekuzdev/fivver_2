package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.auth.entity.Image;

import java.util.Optional;

public interface ImageRepo extends JpaRepository<Image, Integer> {
    Optional<Image> findByName(String name);
}
