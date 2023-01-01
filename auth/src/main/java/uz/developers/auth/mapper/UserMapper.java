package uz.developers.auth.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.developers.auth.dto.RequestUsersDto;
import uz.developers.auth.dto.ResponseUserDto;
import uz.developers.auth.entity.District;
import uz.developers.auth.entity.Region;
import uz.developers.auth.entity.Users;
import uz.developers.auth.repo.DistrictRepo;
import uz.developers.auth.repo.RegionRepo;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RegionRepo regionRepo;
    private final DistrictRepo districtRepo;
    private final LinksMapper linksMapper;
    private final ImageMapper imageMapper;
    public Users toEntity(RequestUsersDto usersDto) throws Exception {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setFirstname(usersDto.getFirstname());
        users.setLastname(usersDto.getLastname());
        users.setProfession(usersDto.getProfession());
        users.setSummary(usersDto.getSummary());
        users.setPassword(usersDto.getPassword());
        users.setPhoneNumber(usersDto.getPhoneNumber());
        {
            Optional<Region> optionalRegion = regionRepo.findById(usersDto.getRegionId());
            if (optionalRegion.isPresent()){
                users.setRegion(optionalRegion.get());
            }else{
                throw new Exception("region not found");
            }
        }
        {
            Optional<District> optionalDistrict = districtRepo.findById(usersDto.getDistrictId());
            if (optionalDistrict.isPresent()){
                users.setDistrict(optionalDistrict.get());
            }else{
                throw new Exception("district not found");
            }
        }
        users.setEmail(usersDto.getEmail());
        users.setLinks(linksMapper.toEntity(usersDto.getLinks()));
        users.setProfilePicture(imageMapper.toEntity(usersDto.getProfilePicture()));
        users.setIsAuthorized(usersDto.getIsAuthorized());
        users.setIsActive(usersDto.getIsActive());
        return users;

    }

    public ResponseUserDto toDto(Users user){
        ResponseUserDto userDto = new ResponseUserDto();
        userDto.setId(user.getId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setProfession(user.getProfession());
        userDto.setSummary(user.getSummary());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setDistrict(user.getDistrict().getName());
        userDto.setRegion(user.getRegion().getName());
        userDto.setLinks(linksMapper.toDto(user.getLinks()));
        return userDto;
    }
}
