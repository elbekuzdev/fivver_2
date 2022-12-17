package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.HiringPartnerDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.Hashtag;
import uz.developers.main.entity.HiringPartner;
import uz.developers.main.entity.Partner;
import uz.developers.main.entity.Users;
import uz.developers.main.mapper.HiringPartnerMapper;
import uz.developers.main.repo.HashtagRepo;
import uz.developers.main.repo.HiringPartnerRepo;
import uz.developers.main.repo.PartnerRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HiringPartnerService {
    private final HiringPartnerRepo hiringPartnerRepo;
    private final PartnerRepo partnerRepo;
    private final HashtagRepo hashtagsRepo;
    private final UserService userService;

    public ResponseDto save(HiringPartnerDto hiringPartnerDto) {
        System.out.println("salom");
        HiringPartner hiringPartner = HiringPartnerMapper.toEntity(hiringPartnerDto);
        hiringPartner.setUser(getCurrentUser());
        HiringPartner save = hiringPartnerRepo.save(hiringPartner);
        if (save.getId() > 0) {
            return ResponseDto.getSuccess(save);
        }
        return ResponseDto.getSuccess(202, "not saved");
    }

    public List<HiringPartner> getAll() {


        return hiringPartnerRepo.findByIsActive(true);
    }

    public ResponseDto getById(Integer id) {
        Optional<HiringPartner> hp = hiringPartnerRepo.findByIsActiveAndId(true, id);
        if (hp.isPresent()) {
            return ResponseDto.getSuccess(hp);
        }
        return ResponseDto.getSuccess(300, "id is invalid");
    }

    public ResponseDto update(Integer id, HiringPartnerDto hiringPartnerDto) {
        Optional<HiringPartner> hp = hiringPartnerRepo.findById(id);
        if (hp.isPresent()) {
            HiringPartner hiringPartner = hp.get();
            HiringPartner requestHiringPartner = HiringPartnerMapper.toEntity(hiringPartnerDto);
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
            requestHiringPartner.setPartners(partners);
            hashtagsRepo.saveAll(requestHiringPartner.getTags());
            partnerRepo.saveAll(requestHiringPartner.getPartners());
            HiringPartner save = hiringPartnerRepo.save(requestHiringPartner);
            return new ResponseDto(200, "ok", save);
        }
        return ResponseDto.getSuccess(300, "not updated");
    }

    public ResponseDto deleteById(Integer id) {

        Optional<HiringPartner> hp = hiringPartnerRepo.findById(id);
        if (hp.isPresent()) {
            HiringPartner hiringPartner = hp.get();
            hiringPartner.setIsActive(false);
            hiringPartnerRepo.save(hiringPartner);
            return ResponseDto.getSuccess(200, "deleted");

        }
        return ResponseDto.getSuccess(300, "not found");
    }

    private Users getCurrentUser(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (Users) userService.loadUserByUsername(email);
    }
}
