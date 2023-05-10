package com.summercampquest.campquest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.summercampquest.campquest.models.UserPrincipal;
import com.summercampquest.campquest.service.SecurityConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.summercampquest.campquest.service.SecurityConstant.*;
import static java.util.Arrays.stream;


@Component
public class JWTTokenProvider {

    //app.prop db configs
    @Value("${jwt.secret}")
    private String secret;


    public String generateJWTToken(UserPrincipal userPrincipal){
        String[] claims = getClaimsFromUser(userPrincipal);

        Integer expiry = (int) (new Date().getTime() + EXPIRATION_TIME);


        String sign = JWT.create().withIssuer(GET_ARRAYS_LLC).withAudience(GET_ARRAYS_ADMINISTRATION).withIssuedAt(new Date()).withSubject(userPrincipal.getUsername()).withArrayClaim(AUTHORITIES, claims).withExpiresAt(Instant.ofEpochSecond(expiry)).sign(Algorithm.HMAC512(secret.getBytes()));
        return sign;
    }

    
    public List<GrantedAuthority> getAuthorities(String token){
        String[] claims = getClaimsFromToken(token);

        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request){
        UsernamePasswordAuthenticationToken userPasswordAutToken = new UsernamePasswordAuthenticationToken(username, null, authorities);

        userPasswordAutToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return userPasswordAutToken;

    }

    public boolean isTokenValid(String username, String token){
        JWTVerifier verifier = getJWTVerifier();
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier,token);
    }

    public String getSubject(String token){
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier  = getJWTVerifier();
        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try{
            Algorithm algorithm = Algorithm.HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(GET_ARRAYS_LLC).build();
        }catch (JWTVerificationException exception){
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }

    public String[] getClaimsFromUser(UserPrincipal userPrincipal){
        List<String> authorities = new ArrayList<>();

        for(GrantedAuthority grantedAuthority:
            userPrincipal.getAuthorities()){
            authorities.add(grantedAuthority.getAuthority());
        }

        return authorities.toArray(new String[0]);

    }




}





















