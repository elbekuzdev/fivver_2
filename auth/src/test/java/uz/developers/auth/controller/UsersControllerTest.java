package uz.developers.auth.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import uz.developers.auth.dto.ImageDto;
import uz.developers.auth.dto.RequestCommentDto;
import uz.developers.auth.dto.RequestUsersDto;
import uz.developers.auth.service.CommentService;
import uz.developers.auth.service.ImageService;
import uz.developers.auth.service.RegionService;
import uz.developers.auth.service.UserService;

@ContextConfiguration(classes = {UsersController.class})
@ExtendWith(SpringExtension.class)
class UsersControllerTest {
    @MockBean
    private CommentService commentService;

    @MockBean
    private ImageService imageService;

    @MockBean
    private RegionService regionService;

    @MockBean
    private UserService userService;

    @Autowired
    private UsersController usersController;

    /**
     * Method under test: {@link UsersController#findAllDistrict(Integer)}
     */
    @Test
    void testFindAllDistrict2() throws Exception {
        when((ResponseEntity<Object>) regionService.findByRegionId((Integer) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/auth/getDistrictByRegionId/{regionId}", 123);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#getComments(Integer)}
     */
    @Test
    void testGetComments() throws Exception {
        when((ResponseEntity<Object>) commentService.getById(anyInt()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/getComments/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#save(RequestUsersDto)}
     */
    @Test
    void testSave() throws Exception {
        when((ResponseEntity<Object>) userService.save((RequestUsersDto) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        RequestUsersDto requestUsersDto = new RequestUsersDto();
        requestUsersDto.setDistrictId(123);
        requestUsersDto.setEmail("jane.doe@example.org");
        requestUsersDto.setFirstname("Jane");
        requestUsersDto.setId(123);
        requestUsersDto.setIsActive(true);
        requestUsersDto.setIsAuthorized(true);
        requestUsersDto.setLastname("Doe");
        requestUsersDto.setLinks(new HashSet<>());
        requestUsersDto.setPassword("iloveyou");
        requestUsersDto.setPhoneNumber("4105551212");
        requestUsersDto.setProfession("Profession");
        requestUsersDto.setProfilePicture(new ImageDto());
        requestUsersDto.setRegionId(123);
        requestUsersDto.setSummary("Summary");
        String content = (new ObjectMapper()).writeValueAsString(requestUsersDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#saveComment(RequestCommentDto)}
     */
    @Test
    void testSaveComment() throws Exception {
        when((ResponseEntity<Object>) commentService.save((RequestCommentDto) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        RequestCommentDto requestCommentDto = new RequestCommentDto();
        requestCommentDto.setCreationTime(timestamp);
        requestCommentDto.setFromUserId(123);
        requestCommentDto.setId(1);
        requestCommentDto.setText("Text");
        requestCommentDto.setToUserId(123);
        String content = (new ObjectMapper()).writeValueAsString(requestCommentDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/saveComment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#update(RequestUsersDto)}
     */
    @Test
    void testUpdate() throws Exception {
        when((ResponseEntity<Object>) userService.update((RequestUsersDto) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        RequestUsersDto requestUsersDto = new RequestUsersDto();
        requestUsersDto.setDistrictId(123);
        requestUsersDto.setEmail("jane.doe@example.org");
        requestUsersDto.setFirstname("Jane");
        requestUsersDto.setId(123);
        requestUsersDto.setIsActive(true);
        requestUsersDto.setIsAuthorized(true);
        requestUsersDto.setLastname("Doe");
        requestUsersDto.setLinks(new HashSet<>());
        requestUsersDto.setPassword("iloveyou");
        requestUsersDto.setPhoneNumber("4105551212");
        requestUsersDto.setProfession("Profession");
        requestUsersDto.setProfilePicture(new ImageDto());
        requestUsersDto.setRegionId(123);
        requestUsersDto.setSummary("Summary");
        String content = (new ObjectMapper()).writeValueAsString(requestUsersDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/auth/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#login(String, String)}
     */
    @Test
    void testLogin() throws Exception {
        when((ResponseEntity<Object>) userService.login((String) any(), (String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                .param("password", "foo")
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#findByEmail(String)}
     */
    @Test
    void testFindByEmail() throws Exception {
        when((ResponseEntity<Object>) userService.findByEmail((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/getByEmail/{email}",
                "jane.doe@example.org");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#changePassword(Integer, String, String)}
     */
    @Test
    void testChangePassword() throws Exception {
        when((ResponseEntity<Object>) userService.changePassword((Integer) any(), (String) any(), (String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/auth/changePassword/{id}", 1)
                .param("newPassword", "foo")
                .param("oldPassword", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#findAllRegion()}
     */
    @Test
    void testFindAllRegion() throws Exception {
        when((ResponseEntity<Object>) regionService.findAllRegion())
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/getRegions");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#findAllDistrict()}
     */
    @Test
    void testFindAllDistrict() throws Exception {
        when((ResponseEntity<Object>) regionService.findAllDistrict())
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/getDistricts");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#getPhoto(Integer)}
     */
    @Test
    void testGetPhoto() throws Exception {
        when((ResponseEntity<Object>) imageService.getPhoto((Integer) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/getPhoto/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link UsersController#savePhoto(Integer, MultipartFile)}
     */
    @Test
    void testSavePhoto() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/auth/addPhoto/{userId}", "Uri Variables",
                "Uri Variables");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("file", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

