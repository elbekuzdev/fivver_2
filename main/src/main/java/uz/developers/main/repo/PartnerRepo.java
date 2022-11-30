package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.Partner;

import java.util.Optional;
@Repository
public interface PartnerRepo extends JpaRepository<Partner, Integer> {


    void deleteById(Optional<Partner> hp);
}
