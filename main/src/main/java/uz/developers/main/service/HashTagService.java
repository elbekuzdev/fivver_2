package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.Hashtag;
import uz.developers.main.repo.HashtagRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashTagService {
    private final HashtagRepo hashtagRepo;

    public ResponseEntity<ResponseDto> delete(Integer id){
        Optional<Hashtag> byId = hashtagRepo.findById(id);
        if (byId.isPresent()){
            hashtagRepo.deleteById(id);
            return ResponseEntity.ok(ResponseDto.getSuccess(200, "deleted"));

        }
        return ResponseEntity.ok(ResponseDto.getSuccess(234, "not deleted"));
    }
}
