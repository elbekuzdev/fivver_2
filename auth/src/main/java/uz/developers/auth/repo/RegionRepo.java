package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.auth.entity.Region;
@Repository

public interface RegionRepo extends JpaRepository<Region, Integer> {
}
