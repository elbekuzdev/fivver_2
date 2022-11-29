package uz.developers.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.developers.auth.dto.RequestUsersDto;
import uz.developers.auth.dto.ResponseDto;
import uz.developers.auth.entity.Links;
import uz.developers.auth.entity.Users;
import uz.developers.auth.mapper.UserMapper;
import uz.developers.auth.repo.LinksRepo;
import uz.developers.auth.repo.UserRepo;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final LinksRepo linksRepo;

    public ResponseEntity<?> save(RequestUsersDto usersDto) {
        try {
            Users user = userMapper.toEntity(usersDto);
            if (!userRepo.existsByEmailAndIsActive(user.getEmail(), true)) {
                if (!userRepo.existsByPhoneNumberAndIsActive(user.getPhoneNumber(), true)) {
                    if (Objects.equals(user.getDistrict().getRegion().getId(), user.getRegion().getId())) {
                        try {
                            Users save = userRepo.save(user);
                            return ResponseEntity.ok(new ResponseDto(200, "saved", toResponse(save)));
                        } catch (Exception e) {
                            return ResponseEntity.ok(ResponseDto.getSuccess(205, "not saved"));
                        }
                    } else {
                        return ResponseEntity.ok(ResponseDto.getSuccess(205, "district not in region"));
                    }
                } else {
                    return ResponseEntity.ok(ResponseDto.getSuccess(205, "phone number is exists"));
                }

            } else {
                return ResponseEntity.ok(ResponseDto.getSuccess(205, "email is exists"));
            }

        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.getSuccess(205, "region or district not found"));
        }
    }
    public ResponseEntity<?> update(RequestUsersDto usersDto) {
        try {
            Optional<Users> optionalUser = userRepo.findById(usersDto.getId());
            try {
                Users user = userMapper.toEntity(usersDto);

                if (optionalUser.isPresent()) {
                    Users currentUser = optionalUser.get();
                    if (user.getEmail().equals(currentUser.getEmail()) || !userRepo.existsByEmailAndIsActive(user.getEmail(), true)) {
                        if (user.getPhoneNumber().equals(currentUser.getPhoneNumber()) || !userRepo.existsByPhoneNumberAndIsActive(user.getPhoneNumber(), true)) {
                            if (Objects.equals(user.getDistrict().getRegion().getId(), user.getRegion().getId())) {
                                try {
                                    concat(user, currentUser);
                                    System.out.println(user.getLinks());
                                    System.out.println(currentUser.getLinks());
                                    try {
                                        linksRepo.saveAll(user.getLinks());
                                        userRepo.save(user);
                                        return ResponseEntity.ok(new ResponseDto(200, "saved", toResponse(user)));
                                    }catch (DataIntegrityViolationException e){
                                        return ResponseEntity.ok(new ResponseDto(200, "saved", toResponse(user)));
                                    }catch (Exception e){
                                        return ResponseEntity.ok(ResponseDto.getSuccess(200, "not saved"));
                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return ResponseEntity.ok(ResponseDto.getSuccess(205, "not saved"));
                                }
                            } else {
                                return ResponseEntity.ok(ResponseDto.getSuccess(205, "district not in region"));
                            }
                        } else {
                            return ResponseEntity.ok(ResponseDto.getSuccess(205, "phone number is exists"));
                        }

                    } else {
                        return ResponseEntity.ok(ResponseDto.getSuccess(205, "email is exists"));
                    }
                } else {
                    return ResponseEntity.ok(ResponseDto.getSuccess(205, "user not found"));
                }

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.ok(ResponseDto.getSuccess(205, "region or district not found"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(ResponseDto.getSuccess(205, "id null"));

        }

    }
    public ResponseEntity<?> findByEmail(String email){
        Optional<Users> optionalUsers = userRepo.findByEmailAndIsActive(email, true);
        if (optionalUsers.isPresent()){
            Users user = optionalUsers.get();
            return ResponseEntity.ok(new ResponseDto(200, "ok", toResponse(user)));
        }else{
            return ResponseEntity.ok(ResponseDto.getSuccess(205, "user not found"));
        }
    }
    private void concat(Users user, Users currentUser){
        if (user.getFirstname() == null){
            user.setFirstname(currentUser.getFirstname());
        }
        if (user.getLastname() == null){
            user.setLastname(currentUser.getLastname());
        }
        if (user.getProfession() == null){
            user.setProfession(currentUser.getProfession());
        }
        if (user.getProfession() == null){
            user.setProfession(currentUser.getProfession());
        }
        if (user.getSummary() == null){
            user.setSummary(currentUser.getSummary());
        }
        if (user.getSummary() == null){
            user.setSummary(currentUser.getSummary());
        }
        if(user.getRegion() == null){
            user.setRegion(currentUser.getRegion());
        }
        if(user.getRegion() == null){
            user.setRegion(currentUser.getRegion());
        }
        if(user.getDistrict() == null){
            user.setDistrict(currentUser.getDistrict());
        }
        if(user.getPhoneNumber() == null){
            user.setPhoneNumber(currentUser.getPhoneNumber());
        }
        if(user.getEmail() == null){
            user.setEmail(currentUser.getEmail());
        }
        if(user.getRegion() == null){
            user.setRegion(currentUser.getRegion());
        }
        user.setPassword(currentUser.getPassword());
        user.setProfilePicture(currentUser.getProfilePicture());
        Set<Links> links;
        if (user.getLinks() != null){
            links = user.getLinks();
        }else{
            links = new HashSet<>();
        }
        links.addAll(currentUser.getLinks());
        user.setLinks(links);
    }
    private Map<String, Object> toResponse(Users user){
        Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("firstname", user.getFirstname());
            response.put("lastname", user.getLastname());
            response.put("profession", user.getProfession());
            response.put("summary", user.getSummary());
            response.put("region", user.getRegion().getName());
            response.put("district", user.getDistrict().getName());
            response.put("phone_number", user.getPhoneNumber());
            response.put("email", user.getEmail());
            response.put("links", user.getLinks());
        return response;
    }
}
