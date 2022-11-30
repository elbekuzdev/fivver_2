package uz.developers.main.mapper;

import uz.developers.main.dto.HiringDto;
import uz.developers.main.entity.Hiring;

public class HiringMapper {
    public static Hiring toEntity(HiringDto hiringDto){
        return new Hiring(hiringDto.getId(), hiringDto.getTitle(), hiringDto.getDescription(), hiringDto.getState(), hiringDto.getUser(), hiringDto.getStartPrice(), hiringDto.getPrice(), HashtagsMapper.toEntity(hiringDto.getTags()), hiringDto.getCreationTime(), hiringDto.isActive());
    }
    public static HiringDto toDto(Hiring hiring){
        return new HiringDto(hiring.getId(), hiring.getTitle(), hiring.getDescription(), hiring.getState(), hiring.getUser(), hiring.getStartPrice(), hiring.getPrice(), HashtagsMapper.toDto(hiring.getTags()), hiring.getCreationTime(), hiring.getIsActive());
    }




}
