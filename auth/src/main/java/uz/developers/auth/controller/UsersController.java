package uz.developers.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developers.auth.dto.RequestUsersDto;
import uz.developers.auth.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UsersController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid RequestUsersDto usersDto){
        return userService.save(usersDto);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid RequestUsersDto usersDto){
        return userService.update(usersDto);
    }
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }


}
