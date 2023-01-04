package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.HashTagDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.Hashtag;
import uz.developers.main.entity.Hiring;
import uz.developers.main.entity.HiringPartner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final HiringService hiringService;
    private final HiringPartnerService hiringPartnerService;

    public ResponseDto findElonByTitleAndTags(String title, List<HashTagDto> tags){
        List<Hiring> hiringList = hiringService.getAllByTitleAndTag(title, tags);
        List<HiringPartner> partnerList = hiringPartnerService.getAllByTitleAndTag(title, tags);
        List<Object> list = Stream.concat(hiringList.stream(), partnerList.stream()).collect(Collectors.toList());

        return ResponseDto.getSuccess(list);
    }

    public static Boolean isAvailableInList(Set<Hashtag> set, List<HashTagDto> list){
        for (Hashtag hashtag : set) {
            if (list.contains(hashtag.getName())){
                return true;
            }
        }
        return false;
    }
}
