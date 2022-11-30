package uz.developers.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.service.TokenService;

@RestController
@RequestMapping("/main/token")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/accessToken")
    public ResponseDto getAccessToken(@RequestParam String email, @RequestParam String password){
        return tokenService.jwtToken(email,password);
    }

    @PostMapping("/refreshToken")
    public ResponseDto getRefreshToken(@RequestParam String refreshToken){
        return tokenService.refreshToken(refreshToken);
    }
}
