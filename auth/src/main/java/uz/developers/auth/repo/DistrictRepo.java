package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.auth.entity.District;
@Repository

public interface DistrictRepo extends JpaRepository<District,Integer> {
}
