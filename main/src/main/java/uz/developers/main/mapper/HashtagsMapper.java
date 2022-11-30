package uz.developers.main.mapper;

import uz.developers.main.dto.HashTagDto;
import uz.developers.main.entity.Hashtag;

import java.util.HashSet;
import java.util.Set;


public class HashtagsMapper {
    public static Hashtag toEntity(HashTagDto hashtagsDto) {
        if (hashtagsDto != null) return new Hashtag(hashtagsDto.getId(), hashtagsDto.getName());
        return new Hashtag();
    }

    public static Set<Hashtag> toEntity(Set<HashTagDto> hashTagDtos) {
        HashSet<Hashtag> hashtags = null;
        if (hashTagDtos != null) {
            hashtags = new HashSet<>();
            for (HashTagDto dto : hashTagDtos) {
                hashtags.add(toEntity(dto));
            }
        }

        return hashtags;
    }

    public static HashTagDto toDto(Hashtag hashtags) {
        if (hashtags != null) return new HashTagDto(hashtags.getId(), hashtags.getName());
        return new HashTagDto();
    }

    public static Set<HashTagDto> toDto(Set<Hashtag> hashTagDtos) {
        HashSet<HashTagDto> tagDtos = null;
        if (hashTagDtos != null) {
            tagDtos = new HashSet<>();
            for (Hashtag dto : hashTagDtos) {
                tagDtos.add(toDto(dto));
            }
        }
        return tagDtos;
    }


}

