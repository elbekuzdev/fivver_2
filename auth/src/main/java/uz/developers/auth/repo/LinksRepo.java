package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.auth.entity.Links;

public interface LinksRepo extends JpaRepository<Links, Integer> {
}
