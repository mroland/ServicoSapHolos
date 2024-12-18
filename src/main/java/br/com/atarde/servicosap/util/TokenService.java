package br.com.atarde.servicosap.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class TokenService {

    private static final String SECRET_KEY = "!SapWebNoJornalHana@2024";  // Substitua por uma chave forte
    public static final long EXPIRATION_TIME = 3600000;  // 1 hora de expiração

    // Gera um token JWT
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Valida o token JWT
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            return false;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
			return false;
		}
    }

    // Extrai o username (subject) do token
    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
