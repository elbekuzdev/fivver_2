package uz.developers.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.entity.RefreshToken;
import uz.developers.main.entity.Users;
import uz.developers.main.repo.RefreshTokenRepo;
import uz.developers.main.security.JwtTokenUtil;
import uz.developers.main.security.RefreshTokenUtil;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final RefreshTokenRepo refreshTokenRepository;
    private final RefreshTokenUtil refreshTokenUtil;

    @Transactional
    public ResponseDto jwtToken(String email, String password){
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String jwt = jwtTokenUtil.jwtGenerator((UserDetails) authenticate.getPrincipal());
            RefreshToken refreshToken = refreshTokenUtil.generateRefreshToken((Users) authenticate.getPrincipal());
            return getResponseModel(jwt, refreshToken);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
    public ResponseDto refreshToken(String refreshToken){
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);
        if (optionalRefreshToken.isPresent() && optionalRefreshToken.get().getExpireDate().getTime()>=new Date().getTime()){
            Users user = optionalRefreshToken.get().getUser();
            refreshTokenRepository.delete(optionalRefreshToken.get());
            String jwt = jwtTokenUtil.jwtGenerator(user);
            RefreshToken generateRefreshToken = refreshTokenUtil.generateRefreshToken(user);
            return getResponseModel(jwt, generateRefreshToken);
        }else {
            return new ResponseDto(401,"Refresh token is not valid",null);
        }

    }

    private ResponseDto getResponseModel(String jwt, RefreshToken generateRefreshToken) {
        refreshTokenRepository.save(generateRefreshToken);
        Map<String,Object> map = new HashMap<>();
        map.put("jwt",jwt);
        map.put("expiry_time",1000*60);
        map.put("issued_at",new Date());
        map.put("refreshToken",generateRefreshToken.getRefreshToken());
        return ResponseDto.getSuccess(map);
    }


}
