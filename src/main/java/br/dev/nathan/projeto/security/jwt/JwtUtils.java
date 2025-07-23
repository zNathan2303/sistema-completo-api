package br.dev.nathan.projeto.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.dev.nathan.projeto.service.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

// dizer para o spring que isso e um componente para futuramente utiliza-lo por injecao de dependencia
@Component
public class JwtUtils {
	
	// password
	@Value("${projeto.jwtSecret}")
	private String jwtSecret;
	
	// quando tempo vai ficar valido o token
	@Value("${projeto.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	public String generateTokenFromUserDetailsImpl(UserDetailsImpl userData) {
		return Jwts.builder().subject(userData.getUsername())
				.issuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(getSigninKey(), SignatureAlgorithm.HS512).compact();
	}
	
	public Key getSigninKey() {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
		return key;
	}
	
	public String getUsernameToken(String token) {
		return Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJwt(authToken);
			return true;
		} catch (MalformedJwtException e) {
			System.out.println("Token inválido " + e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.println("Token expirado " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println("Token não suportado " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Argumento inválido " + e.getMessage());
		}
		
		return false;
	}

}
