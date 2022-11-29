package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.auth.entity.District;

import java.util.List;

@Repository

public interface DistrictRepo extends JpaRepository<District,Integer> {
    List<District> findByRegionId(Integer regionId);
}
