package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.HiringDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.Hashtag;
import uz.developers.main.entity.Hiring;
import uz.developers.main.mapper.HiringMapper;
import uz.developers.main.repo.HashtagRepo;
import uz.developers.main.repo.HiringRepo;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HiringService {
    private final HiringRepo hiringRepo;
    private final HashtagRepo hashtagsRepo;

    public ResponseEntity<ResponseDto> save(HiringDto hiringDto) {
        try {
            hiringRepo.save(HiringMapper.toEntity(hiringDto));
            return ResponseEntity.ok(ResponseDto.getSuccess(200, "ok"));
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.getSuccess(211, "not saved"));
        }
    }

    public ResponseEntity<ResponseDto> getById(Integer id) {
        Optional<Hiring> byId = hiringRepo.findByIdAndIsActive(id, true);
        return byId.map(hiring -> ResponseEntity.ok(new ResponseDto(200, "ok", hiring))).orElseGet(() -> ResponseEntity.ok(new ResponseDto(404, "id not found", null)));
    }

    public ResponseEntity<ResponseDto> getAll() {
        List<Hiring> all = hiringRepo.findByIsActive(true);
        LinkedList<HiringDto> hirings = new LinkedList<>();
        for (Hiring h : all) {
            hirings.add(HiringMapper.toDto(h));
        }

        return ResponseEntity.ok(new ResponseDto(200, "ok", hirings));
    }

    public ResponseEntity<ResponseDto> update(Integer id, HiringDto hiringDto) {
        Optional<Hiring> optionalHiring = hiringRepo.findByIdAndIsActive(id, true);
        if (optionalHiring.isPresent()) {
            Hiring hiring = optionalHiring.get();
            Hiring requestHiring = HiringMapper.toEntity(hiringDto);
            Set<Hashtag> tags = null;
            if (requestHiring.getTags() != null) {
                tags = requestHiring.getTags();

            } else {
                tags = new HashSet<>();
            }
            if (hiring.getTags() != null){
                tags.addAll(hiring.getTags());
            }
            requestHiring.setTags(tags);
            hashtagsRepo.saveAll(requestHiring.getTags());
            Hiring save = hiringRepo.save(requestHiring);
            return ResponseEntity.ok(new ResponseDto(200, "ok", save));
        } else {
            return ResponseEntity.ok(new ResponseDto(404, "id not found", null));
        }
    }

    public ResponseEntity<ResponseDto> deleteById(Integer id) {
        Optional<Hiring> byIdAndActive = hiringRepo.findByIdAndIsActive(id, true);
        if (byIdAndActive.isPresent()) {
            Hiring hiring = byIdAndActive.get();
            hiring.setIsActive(false);
            hiringRepo.save(hiring);
            return ResponseEntity.ok(new ResponseDto(200, "deleted", null));
        } else {
            return ResponseEntity.ok(new ResponseDto(200, "id not found", null));

        }
    }
}
