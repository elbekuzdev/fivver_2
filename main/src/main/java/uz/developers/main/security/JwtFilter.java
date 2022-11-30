package uz.developers.main.security;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.developers.main.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String email =null;
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                email = jwtTokenUtil.getEmailFromJwt(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Not Valid");
            } catch (ExpiredJwtException e) {
                System.out.println("Expired JWT token");
            } catch (Exception e) {
                System.out.println("Something wrong");
            }
            if (email != null && jwtTokenUtil.checkExpireDate(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
                System.out.println(userDetails.getAuthorities() + " HEllo");
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
