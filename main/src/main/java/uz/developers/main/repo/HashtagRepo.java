package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.Hashtag;

@Repository
public interface HashtagRepo extends JpaRepository<Hashtag, Integer> {

}
