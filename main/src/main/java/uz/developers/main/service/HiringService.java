package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.HashTagDto;
import uz.developers.main.dto.HiringDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.dto.ResponseHiringDto;
import uz.developers.main.entity.Hashtag;
import uz.developers.main.entity.Hiring;
import uz.developers.main.entity.Users;
import uz.developers.main.mapper.HiringMapper;
import uz.developers.main.repo.HashtagRepo;
import uz.developers.main.repo.HiringRepo;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HiringService {
    private final HiringRepo hiringRepo;
    private final HashtagRepo hashtagsRepo;
    private final UserService userService;

    public ResponseEntity<ResponseDto> save(HiringDto hiringDto) {
        try {
            Hiring hiring = HiringMapper.toEntity(hiringDto);
            hiring.setUser(getCurrentUser());
            hiringRepo.save(hiring);
            return ResponseEntity.ok(ResponseDto.getSuccess(200, "saved"));
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.getSuccess(211, "not saved"));
        }
    }

    public ResponseEntity<ResponseDto> getById(Integer id) {
        Optional<Hiring> byId = hiringRepo.findByIdAndIsActive(id, true);
        return byId.map(hiring -> ResponseEntity.ok(new ResponseDto(200, "ok", HiringMapper.toDto(hiring)))).orElseGet(() -> ResponseEntity.ok(new ResponseDto(404, "id not found", null)));
    }

    public ResponseEntity<ResponseDto> getAll() {
        List<Hiring> all = hiringRepo.findByIsActive(true);
        LinkedList<ResponseHiringDto> hirings = new LinkedList<>();
        for (Hiring h : all) {
            hirings.add(HiringMapper.toDto(h));
        }
        return ResponseEntity.ok(new ResponseDto(200, "ok", hirings));
    }

    public ResponseEntity<ResponseDto> update(HiringDto hiringDto) {
        Optional<Hiring> optionalHiring = hiringRepo.findByIdAndIsActive(hiringDto.getId(), true);
        if (optionalHiring.isPresent() && hiringDto.getUser().getId() == getCurrentUser().getId()) {
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
        if (byIdAndActive.isPresent() && byIdAndActive.get().getUser().getId() == getCurrentUser().getId()) {
            Hiring hiring = byIdAndActive.get();
            hiring.setIsActive(false);
            hiringRepo.save(hiring);
            return ResponseEntity.ok(new ResponseDto(200, "deleted", null));
        } else {
            return ResponseEntity.ok(new ResponseDto(200, "id not found", null));

        }
    }
    private Users getCurrentUser(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  (Users) userService.loadUserByUsername(username);
    }

    public List<Hiring> getAllByTitleAndTag(String title, List<HashTagDto> hashtags){
        List<Hiring> elonlar = hiringRepo.findAll();
        List<Hiring> matchedElonlar = new LinkedList<>();
        for (Hiring elon : elonlar) {if (elon.getTitle().contains(title))matchedElonlar.add(elon);}
        for (Hiring elon : elonlar) {if (SearchService.isAvailableInList(elon.getTags(),hashtags))matchedElonlar.add(elon);}
        return matchedElonlar;
    }


}
