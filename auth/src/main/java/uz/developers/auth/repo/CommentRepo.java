package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.auth.entity.Comment;
@Repository

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
