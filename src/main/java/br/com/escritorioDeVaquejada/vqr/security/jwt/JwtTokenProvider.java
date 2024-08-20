package br.com.escritorioDeVaquejada.vqr.security.jwt;

import br.com.escritorioDeVaquejada.vqr.exception.InvalidJwtAuthenticationException;
import br.com.escritorioDeVaquejada.vqr.vo.TokenVO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000L;
    @Value("${security.jwt.refresh-token.expire-length:10800000}")
    private long validityRefreshTokenInMilliseconds = 10800000L;

    @Autowired
    private UserDetailsService userDetailsService;
    Algorithm algorithm = null;

    @PostConstruct
    private void init(){
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
        this.algorithm = Algorithm.HMAC256(this.secretKey.getBytes());
    }

    public TokenVO createAccessToken(String username, List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime() + this.validityInMilliseconds);
        String accessToken = getAccessToken(username, roles, now, validity);
        String refreshToken = getRefreshToken(username, roles, now);

        return new TokenVO(username, true, now, validity, accessToken, refreshToken);
    }

    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = decodedJWT(token);
        UserDetails userDetails =
                this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                "",
                userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateToken(String token) throws InvalidJwtAuthenticationException{
        DecodedJWT decodedJWT = decodedJWT(token);
        try{
            return !decodedJWT.getExpiresAt().before(new Date());
        }catch (Exception e){
            throw new InvalidJwtAuthenticationException("Expired or invalid token!");
        }
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + this.validityRefreshTokenInMilliseconds))
                .withSubject(username)
                .sign(this.algorithm)
                .strip();
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(this.algorithm)
                .strip();
    }

    private DecodedJWT decodedJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
