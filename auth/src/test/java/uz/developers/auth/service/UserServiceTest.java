package uz.developers.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.developers.auth.dto.RequestUsersDto;
import uz.developers.auth.dto.ResponseDto;
import uz.developers.auth.dto.ResponseUserDto;
import uz.developers.auth.entity.District;
import uz.developers.auth.entity.Image;
import uz.developers.auth.entity.Region;
import uz.developers.auth.entity.Users;
import uz.developers.auth.mapper.UserMapper;
import uz.developers.auth.repo.LinksRepo;
import uz.developers.auth.repo.UserRepo;
import uz.developers.auth.util.PasswordUtils;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private LinksRepo linksRepo;

    @MockBean
    private PasswordUtils passwordUtils;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#save(RequestUsersDto)}
     */
    @Test
    void testSave() throws Exception {
        Region region = new Region();
        region.setId(1);
        region.setName("Name");

        District district = new District();
        district.setId(1);
        district.setName("Name");
        district.setRegion(region);

        Image image = new Image();
        image.setId(1);
        image.setImageData("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setType("Type");

        Region region1 = new Region();
        region1.setId(1);
        region1.setName("Name");

        Users users = new Users();
        users.setDistrict(district);
        users.setEmail("jane.doe@example.org");
        users.setFirstname("Jane");
        users.setId(1);
        users.setIsActive(true);
        users.setIsAuthorized(true);
        users.setLastname("Doe");
        users.setLinks(new HashSet<>());
        users.setPassword("iloveyou");
        users.setPhoneNumber("4105551212");
        users.setProfession("Profession");
        users.setProfilePicture(image);
        users.setRegion(region1);
        users.setSummary("Summary");
        when(userRepo.existsByEmailAndIsActive((String) any(), (Boolean) any())).thenReturn(true);
        when(userRepo.existsByPhoneNumberAndIsActive((String) any(), (Boolean) any())).thenReturn(true);
        when(userRepo.save((Users) any())).thenReturn(users);

        Region region2 = new Region();
        region2.setId(1);
        region2.setName("Name");

        District district1 = new District();
        district1.setId(1);
        district1.setName("Name");
        district1.setRegion(region2);

        Image image1 = new Image();
        image1.setId(1);
        image1.setImageData("AAAAAAAA".getBytes("UTF-8"));
        image1.setName("Name");
        image1.setType("Type");

        Region region3 = new Region();
        region3.setId(1);
        region3.setName("Name");

        Users users1 = new Users();
        users1.setDistrict(district1);
        users1.setEmail("jane.doe@example.org");
        users1.setFirstname("Jane");
        users1.setId(1);
        users1.setIsActive(true);
        users1.setIsAuthorized(true);
        users1.setLastname("Doe");
        users1.setLinks(new HashSet<>());
        users1.setPassword("iloveyou");
        users1.setPhoneNumber("4105551212");
        users1.setProfession("Profession");
        users1.setProfilePicture(image1);
        users1.setRegion(region3);
        users1.setSummary("Summary");
        when(userMapper.toEntity((RequestUsersDto) any())).thenReturn(users1);
        ResponseEntity<?> actualSaveResult = userService.save(new RequestUsersDto());
        assertTrue(actualSaveResult.hasBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualSaveResult.getStatusCode());
        assertEquals("email is exists", ((ResponseDto) actualSaveResult.getBody()).getMessage());
        assertNull(((ResponseDto) actualSaveResult.getBody()).getObject());
        assertEquals(205, ((ResponseDto) actualSaveResult.getBody()).getStatusCode().intValue());
        verify(userRepo).existsByEmailAndIsActive((String) any(), (Boolean) any());
        verify(userMapper).toEntity((RequestUsersDto) any());
    }

    /**
     * Method under test: {@link UserService#save(RequestUsersDto)}
     */
    @Test
    void testSave2() throws Exception {
        when(userRepo.existsByEmailAndIsActive((String) any(), (Boolean) any()))
                .thenThrow(new DataIntegrityViolationException("saved"));
        when(userRepo.existsByPhoneNumberAndIsActive((String) any(), (Boolean) any()))
                .thenThrow(new DataIntegrityViolationException("saved"));
        when(userRepo.save((Users) any())).thenThrow(new DataIntegrityViolationException("saved"));

        Region region = new Region();
        region.setId(1);
        region.setName("Name");

        District district = new District();
        district.setId(1);
        district.setName("Name");
        district.setRegion(region);

        Image image = new Image();
        image.setId(1);
        image.setImageData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        image.setName("Name");
        image.setType("Type");

        Region region1 = new Region();
        region1.setId(1);
        region1.setName("Name");

        Users users = new Users();
        users.setDistrict(district);
        users.setEmail("jane.doe@example.org");
        users.setFirstname("Jane");
        users.setId(1);
        users.setIsActive(true);
        users.setIsAuthorized(true);
        users.setLastname("Doe");
        users.setLinks(new HashSet<>());
        users.setPassword("iloveyou");
        users.setPhoneNumber("4105551212");
        users.setProfession("Profession");
        users.setProfilePicture(image);
        users.setRegion(region1);
        users.setSummary("Summary");
        when(userMapper.toEntity(any())).thenReturn(users);
        ResponseEntity<?> actualSaveResult = userService.save(new RequestUsersDto());
        assertTrue(actualSaveResult.hasBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualSaveResult.getStatusCode());
        assertEquals("region or district not found", ((ResponseDto) actualSaveResult.getBody()).getMessage());
        assertNull(((ResponseDto) actualSaveResult.getBody()).getObject());
        assertEquals(205, ((ResponseDto) actualSaveResult.getBody()).getStatusCode().intValue());
        verify(userRepo).existsByEmailAndIsActive((String) any(), (Boolean) any());
        verify(userMapper).toEntity((RequestUsersDto) any());
    }

    /**
     * Method under test: {@link UserService#save(RequestUsersDto)}
     */
    @Test
    void testSave3() throws Exception {
        Region region = new Region();
        region.setId(1);
        region.setName("Name");

        District district = new District();
        district.setId(1);
        district.setName("Name");
        district.setRegion(region);

        Image image = new Image();
        image.setId(1);
        image.setImageData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        image.setName("Name");
        image.setType("Type");

        Region region1 = new Region();
        region1.setId(1);
        region1.setName("Name");

        Users users = new Users();
        users.setDistrict(district);
        users.setEmail("jane.doe@example.org");
        users.setFirstname("Jane");
        users.setId(1);
        users.setIsActive(true);
        users.setIsAuthorized(true);
        users.setLastname("Doe");
        users.setLinks(new HashSet<>());
        users.setPassword("iloveyou");
        users.setPhoneNumber("4105551212");
        users.setProfession("Profession");
        users.setProfilePicture(image);
        users.setRegion(region1);
        users.setSummary("Summary");
        when(userRepo.existsByEmailAndIsActive((String) any(), (Boolean) any())).thenReturn(false);
        when(userRepo.existsByPhoneNumberAndIsActive((String) any(), (Boolean) any())).thenReturn(true);
        when(userRepo.save((Users) any())).thenReturn(users);

        Region region2 = new Region();
        region2.setId(1);
        region2.setName("Name");

        District district1 = new District();
        district1.setId(1);
        district1.setName("Name");
        district1.setRegion(region2);

        Image image1 = new Image();
        image1.setId(1);
        image1.setImageData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        image1.setName("Name");
        image1.setType("Type");

        Region region3 = new Region();
        region3.setId(1);
        region3.setName("Name");

        Users users1 = new Users();
        users1.setDistrict(district1);
        users1.setEmail("jane.doe@example.org");
        users1.setFirstname("Jane");
        users1.setId(1);
        users1.setIsActive(true);
        users1.setIsAuthorized(true);
        users1.setLastname("Doe");
        users1.setLinks(new HashSet<>());
        users1.setPassword("iloveyou");
        users1.setPhoneNumber("4105551212");
        users1.setProfession("Profession");
        users1.setProfilePicture(image1);
        users1.setRegion(region3);
        users1.setSummary("Summary");
        when(userMapper.toDto((Users) any())).thenReturn(new ResponseUserDto());
        when(userMapper.toEntity((RequestUsersDto) any())).thenReturn(users1);
        when(passwordUtils.encodePassword((String) any())).thenReturn("secret");
        ResponseEntity<?> actualSaveResult = userService.save(new RequestUsersDto());
        assertTrue(actualSaveResult.hasBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualSaveResult.getStatusCode());
        assertEquals("phone number is exists", ((ResponseDto) Objects.requireNonNull(actualSaveResult.getBody())).getMessage());
        assertNull(((ResponseDto) actualSaveResult.getBody()).getObject());
        assertEquals(205, ((ResponseDto) actualSaveResult.getBody()).getStatusCode().intValue());
        verify(userRepo).existsByEmailAndIsActive((String) any(), (Boolean) any());
        verify(userRepo).existsByPhoneNumberAndIsActive((String) any(), (Boolean) any());
        verify(userMapper).toEntity((RequestUsersDto) any());
    }
}

