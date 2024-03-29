/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Seguridad.JWT;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.sedback.SEDBack.Dtos.JwtDTO;
import com.sedback.SEDBack.Seguridad.UsuarioPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author ususario
 */
@Component
public class JwtProvider { //esta clase es el corazón de JWT, donde se crea el token, se valida y se extrae el nombre del usuario.
    
    private static  final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;
    
    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        List<String> perfiles = usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
            .setSubject(usuarioPrincipal.getUsername())
            .claim("perfiles",perfiles)
            .claim("id_user", usuarioPrincipal.getId())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.getBytes())
            .compact();
    }
    
    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }
    
    public Long getIdUserFromToken(String token){
        return Long.parseLong(Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().get("id_user").toString());
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado " +e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado " +e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("token expirado " +e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("token vacío " +e.getMessage());
        } catch (SignatureException e) {
            logger.error("error en la firma " +e.getMessage());
        }
        return false;
    }
    
    public String refreshToken(JwtDTO jwtDto) throws ParseException{
        JWT jwt = JWTParser.parse(jwtDto.getToken());
        JWTClaimsSet claims = jwt.getJWTClaimsSet();
        String nombreUsuario = claims.getSubject();
        Long id_user = (Long) claims.getClaim("id_user");
        List<String> perfiles = (List<String>) claims.getClaim("perfiles");
        return Jwts.builder()
            .setSubject(nombreUsuario)
            .claim("perfiles",perfiles)
            .claim("id_user", id_user)
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.getBytes())
            .compact();  
    }
    
    
}
