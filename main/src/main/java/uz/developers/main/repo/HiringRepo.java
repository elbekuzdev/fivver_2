package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.Hiring;

import java.util.List;
import java.util.Optional;
@Repository
public interface HiringRepo extends JpaRepository<Hiring, Integer> {
    List<Hiring> findByIsActive(Boolean b);
    Optional<Hiring> findByIdAndIsActive(Integer id, Boolean b);
}
