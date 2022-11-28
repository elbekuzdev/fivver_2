package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.auth.entity.District;

public interface DistrictRepo extends JpaRepository<District,Integer> {
}
