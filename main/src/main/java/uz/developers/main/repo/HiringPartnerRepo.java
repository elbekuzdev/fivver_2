package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.HiringPartner;

import java.util.List;
import java.util.Optional;

@Repository
public interface HiringPartnerRepo extends JpaRepository<HiringPartner, Integer> {
    List<HiringPartner> findByIsActive(Boolean b);
    Optional<HiringPartner> findByIsActiveAndId(Boolean b, Integer id);
}
