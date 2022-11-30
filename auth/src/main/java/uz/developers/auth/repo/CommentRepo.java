package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.developers.auth.entity.Comment;

import java.util.List;

@Repository

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findByTo_Id(Integer to);
}
