package uz.developers.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.developers.main.dto.HashTagDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.Hashtag;
import uz.developers.main.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/main/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/getAll")
    public ResponseDto searchByTitleAndTags(String title, List<HashTagDto> hashtags){
        return searchService.findElonByTitleAndTags(title,hashtags);
    }
}
