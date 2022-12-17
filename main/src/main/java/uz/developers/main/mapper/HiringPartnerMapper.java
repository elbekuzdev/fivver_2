package uz.developers.main.mapper;

import uz.developers.main.dto.HiringPartnerDto;
import uz.developers.main.entity.HiringPartner;
import uz.developers.main.entity.Users;

public class HiringPartnerMapper {

    public static HiringPartner toEntity(HiringPartnerDto hiringPartnerDto) {
        if (hiringPartnerDto != null)
            return new HiringPartner(hiringPartnerDto.getId(), hiringPartnerDto.getTitle(), hiringPartnerDto.getDescription(), hiringPartnerDto.getState(), new Users(), hiringPartnerDto.getStartPrice(), hiringPartnerDto.getPrice(), PartnerMapper.toEntity(hiringPartnerDto.getPartners()), HashtagsMapper.toEntity(hiringPartnerDto.getTags()), hiringPartnerDto.getCreationTime(), hiringPartnerDto.isActive());
        return new HiringPartner();
    }

    public static HiringPartnerDto toDto(HiringPartner hiringPartner) {
        if (hiringPartner != null)
            return new HiringPartnerDto(hiringPartner.getId(), hiringPartner.getTitle(), hiringPartner.getDescription(), hiringPartner.getState(), UserMapper.toDto(hiringPartner.getUser()), hiringPartner.getPrice(), hiringPartner.getPrice(), PartnerMapper.toDto(hiringPartner.getPartners()), HashtagsMapper.toDto(hiringPartner.getTags()), hiringPartner.getCreationTime(), hiringPartner.getIsActive());
        return new HiringPartnerDto();
    }

}
