package uz.developers.auth.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.developers.auth.dto.RequestCommentDto;
import uz.developers.auth.entity.Comment;
import uz.developers.auth.entity.Users;
import uz.developers.auth.repo.CommentRepo;
import uz.developers.auth.repo.UserRepo;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CommentMapper {
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;

    public Comment toEntity(RequestCommentDto commentDto) throws Exception {
        Comment comment = new Comment();
        if (commentDto != null){
            comment.setId(commentDto.getId());
            comment.setText(commentDto.getText());
            {
                Optional<Users> optionalUser = userRepo.findByIdAndIsActive(commentDto.getFromUserId(), true);
                if (optionalUser.isPresent()) {
                    Users from = optionalUser.get();
                    comment.setFrom(from);
                } else {
                    throw new Exception("from user not found");
                }
            }
            {
                Optional<Users> optionalUser1 = userRepo.findByIdAndIsActive(commentDto.getFromUserId(), true);
                if (optionalUser1.isPresent()) {
                    Users to = optionalUser1.get();
                    comment.setTo(to);
                } else {
                    throw new Exception("to user not found");
                }
            }
            comment.setCreationTime(commentDto.getCreationTime());
        }
        return comment;
    }


}
