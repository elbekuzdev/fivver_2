package uz.developers.auth.mapper;

import org.springframework.stereotype.Component;
import uz.developers.auth.dto.LinksDto;
import uz.developers.auth.entity.Links;

import java.util.HashSet;
import java.util.Set;

@Component
public class LinksMapper {
    public  Links toEntity(LinksDto linksdto) {
        Links link = new Links();
        link.setId(linksdto.getId());
        link.setName(linksdto.getName());
        link.setUrl(linksdto.getUrl());
        return link;
    }

    public  Set<Links> toEntity(Set<LinksDto> linksDtos) {
        HashSet<Links> links = new HashSet<>();
        if (linksDtos != null) for (LinksDto linksDto : linksDtos)
            links.add(toEntity(linksDto));
        return links;
    }

    public  LinksDto toDto(Links links) {
        LinksDto linksDto = new LinksDto();
        linksDto.setId(links.getId());
        linksDto.setName(links.getName());
        linksDto.setUrl(links.getUrl());
        return linksDto;
    }

    public  Set<LinksDto> toDto(Set<Links> links) {
        HashSet<LinksDto> linksDtos = new HashSet<>();
        if (links != null) for (Links link : links)
            linksDtos.add(toDto(link));
        return linksDtos;
    }
}
