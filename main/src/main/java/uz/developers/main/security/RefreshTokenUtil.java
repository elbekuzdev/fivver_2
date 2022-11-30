package uz.developers.main.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.developers.main.entity.RefreshToken;
import uz.developers.main.entity.Users;

import java.util.Date;
import java.util.UUID;

@Component
public class RefreshTokenUtil {

    @Value("${refresh.token.expiration.time}")
    private long expirationTime;

    public RefreshToken generateRefreshToken(Users users){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(new Date().getTime()+expirationTime));
        refreshToken.setUser(users);
        return refreshToken;
    }
}
