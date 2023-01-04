package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.HashTagDto;
import uz.developers.main.dto.HiringPartnerDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.*;
import uz.developers.main.mapper.HiringPartnerMapper;
import uz.developers.main.repo.HashtagRepo;
import uz.developers.main.repo.HiringPartnerRepo;
import uz.developers.main.repo.PartnerRepo;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HiringPartnerService {
    private final HiringPartnerRepo hiringPartnerRepo;
    private final PartnerRepo partnerRepo;
    private final HashtagRepo hashtagsRepo;
    private final UserService userService;

    public ResponseDto save(HiringPartnerDto hiringPartnerDto) {
        HiringPartner hiringPartner = HiringPartnerMapper.toEntity(hiringPartnerDto);
        hiringPartner.setUser(getCurrentUser());
        HiringPartner save = hiringPartnerRepo.save(hiringPartner);
        if (save.getId() > 0) {
            return ResponseDto.getSuccess(200, "saved");
        }
        return ResponseDto.getSuccess(202, "not saved");
    }

    public ResponseDto getAll() {
        List<HiringPartnerDto> partnerDtos = new LinkedList<>();
        for (HiringPartner hiringPartner : hiringPartnerRepo.findByIsActive(true)) {
            partnerDtos.add(HiringPartnerMapper.toDto(hiringPartner));
        }
        return ResponseDto.getSuccess(partnerDtos);
    }

    public ResponseDto getById(Integer id) {
        Optional<HiringPartner> hp = hiringPartnerRepo.findByIsActiveAndId(true, id);
        return hp.map(hiringPartner -> ResponseDto.getSuccess(HiringPartnerMapper.toDto(hiringPartner))).orElseGet(() -> ResponseDto.getSuccess(300, "id is invalid"));
    }

    public ResponseDto update(HiringPartnerDto hiringPartnerDto) {
        Optional<HiringPartner> hp = hiringPartnerRepo.findById(hiringPartnerDto.getId());
        if (hp.isPresent() && hp.get().getUser().getId() == getCurrentUser().getId()) {
            HiringPartner hiringPartner = hp.get();
            HiringPartner requestHiringPartner = HiringPartnerMapper.toEntity(hiringPartnerDto);
            requestHiringPartner.setUser(hiringPartner.getUser());
            Set<Hashtag> hashtags = null;

            if (requestHiringPartner.getTags() != null) {
                hashtags = requestHiringPartner.getTags();
            } else {
                hashtags = new HashSet<>();
            }
            if (hiringPartner.getTags() != null) {
                hashtags.addAll(hiringPartner.getTags());
            }

            requestHiringPartner.setTags(hashtags);
            Set<Partner> partners = null;
            if (requestHiringPartner.getPartners() != null) partners = requestHiringPartner.getPartners();
            else partners = new HashSet<>();
            if (hiringPartner.getPartners() != null) {
                partners.addAll(hiringPartner.getPartners());
            }
            try {

                requestHiringPartner.setPartners(partners);
                hashtagsRepo.saveAll(requestHiringPartner.getTags());
                partnerRepo.saveAll(requestHiringPartner.getPartners());

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                try {
                    HiringPartner save = hiringPartnerRepo.save(requestHiringPartner);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return new ResponseDto(200, "ok", HiringPartnerMapper.toDto(requestHiringPartner));

        } else {
            return ResponseDto.getSuccess(300, "user not found");

        }

    }

    public ResponseDto deleteById(Integer id) {

        Optional<HiringPartner> hp = hiringPartnerRepo.findById(id);
        if (hp.isPresent() && hp.get().getUser().getId() == getCurrentUser().getId()) {
            HiringPartner hiringPartner = hp.get();
            hiringPartner.setIsActive(false);
            hiringPartnerRepo.save(hiringPartner);
            return ResponseDto.getSuccess(200, "deleted");

        }
        return ResponseDto.getSuccess(300, "not found");
    }

    private Users getCurrentUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (Users) userService.loadUserByUsername(email);
    }

    public List<HiringPartner> getAllByTitleAndTag(String title, List<HashTagDto> hashtags){
        List<HiringPartner> elonlar = hiringPartnerRepo.findAll();
        List<HiringPartner> matchedElonlar = new LinkedList<>();
        for (HiringPartner elon : elonlar) {if (elon.getTitle().contains(title))matchedElonlar.add(elon);}
        for (HiringPartner elon : elonlar) {if (SearchService.isAvailableInList(elon.getTags(),hashtags))matchedElonlar.add(elon);}
        return matchedElonlar;
    }

}
