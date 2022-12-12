package uz.developers.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.developers.auth.dto.RequestCommentDto;
import uz.developers.auth.dto.RequestUsersDto;
import uz.developers.auth.service.CommentService;
import uz.developers.auth.service.ImageService;
import uz.developers.auth.service.RegionService;
import uz.developers.auth.service.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UsersController {

    private final UserService userService;
    private final RegionService regionService;
    private final ImageService imageService;
    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid RequestUsersDto usersDto) {
        return userService.save(usersDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RequestUsersDto usersDto) {
        return userService.update(usersDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, String password) {
        return userService.login(username, password);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable Integer id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return userService.changePassword(id, oldPassword, newPassword);
    }

    @GetMapping("/getRegions")
    public ResponseEntity<?> findAllRegion() {
        return regionService.findAllRegion();
    }

    @GetMapping("/getDistricts")
    public ResponseEntity<?> findAllDistrict() {
        return regionService.findAllDistrict();
    }

    @GetMapping("/getDistrictByRegionId/{regionId}")
    public ResponseEntity<?> findAllDistrict(@PathVariable Integer regionId) {
        return regionService.findByRegionId(regionId);
    }

    @PostMapping("/addPhoto/{userId}")
    public ResponseEntity<?> savePhoto(@PathVariable Integer userId, @RequestParam MultipartFile file) {
        return imageService.save(file, userId);
    }

    @GetMapping("/getPhoto/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable Integer id) {
        return imageService.getPhoto(id);
    }

    @PostMapping("/saveComment")
    public ResponseEntity<?> saveComment(@RequestBody @Valid RequestCommentDto commentDto){
        return commentService.save(commentDto);
    }

    @GetMapping("/getComments/{id}")
    public ResponseEntity<?> getComments(@PathVariable Integer id){
        return commentService.getById(id);
    }

}
