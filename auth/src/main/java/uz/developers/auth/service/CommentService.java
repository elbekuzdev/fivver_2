package uz.developers.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.developers.auth.dto.RequestCommentDto;
import uz.developers.auth.dto.ResponseDto;
import uz.developers.auth.entity.Comment;
import uz.developers.auth.entity.Users;
import uz.developers.auth.mapper.CommentMapper;
import uz.developers.auth.repo.CommentRepo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final CommentMapper commentMapper;

    public ResponseEntity<?> save(RequestCommentDto commentDto) {
        try {
            Comment comment = commentMapper.toEntity(commentDto);
            try {
                commentRepo.save(comment);
                return ResponseEntity.ok(ResponseDto.getSuccess(200, "saved"));

            } catch (Exception e) {
                return ResponseEntity.ok(ResponseDto.getSuccess(205, "not saved"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.getSuccess(205, "from or to is not found"));
        }
    }

    public ResponseEntity<?> getById(int to) {
        return ResponseEntity.ok(new ResponseDto(200, "ok", commentMapper.toDto(commentRepo.findByTo_Id(to))));
    }


}
