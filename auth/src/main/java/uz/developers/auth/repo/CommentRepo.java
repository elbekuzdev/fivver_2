package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.auth.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
