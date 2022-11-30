package uz.developers.main.mapper;

import uz.developers.main.dto.PartnerDto;
import uz.developers.main.entity.Partner;

import java.util.HashSet;
import java.util.Set;

public class PartnerMapper {
    public static Partner toEntity(PartnerDto partnerDto) {
        if (partnerDto != null)
            return new Partner(partnerDto.getId(), partnerDto.getProfession(), partnerDto.getCount());
        return new Partner();
    }

    public static Set<Partner> toEntity(Set<PartnerDto> partnerDtos) {
        HashSet<Partner> partners = new HashSet<>();
        if (partnerDtos != null)
            for (PartnerDto dto : partnerDtos) {

                partners.add(toEntity(dto));
            }

        return partners;
    }


    public static PartnerDto toDto(Partner partner) {
        if (partner != null)
            return new PartnerDto(partner.getId(), partner.getProfession(), partner.getCount());
        return new PartnerDto();
    }

    public static Set<PartnerDto> toDto(Set<Partner> partnerDtos) {
        HashSet<PartnerDto> dtos = new HashSet<>();
        if (partnerDtos != null)
            for (Partner partner : partnerDtos) {

                dtos.add(toDto(partner));
            }

        return dtos;
    }


}
