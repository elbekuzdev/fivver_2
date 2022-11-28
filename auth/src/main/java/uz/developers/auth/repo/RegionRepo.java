package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.auth.entity.Region;

public interface RegionRepo extends JpaRepository<Region, Integer> {
}
