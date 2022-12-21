package uz.developers.main.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.service.HashTagService;

@RestController
@RequestMapping("/main/hashtag")
@RequiredArgsConstructor
public class HashTagController {
    private final HashTagService hashTagService;

//    @PreAuthorize("hasAnyRole('DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Integer id){
        return hashTagService.delete(id);
    }
}
