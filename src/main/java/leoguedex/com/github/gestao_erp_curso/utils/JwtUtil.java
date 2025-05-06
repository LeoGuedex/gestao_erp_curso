package leoguedex.com.github.gestao_erp_curso.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import leoguedex.com.github.gestao_erp_curso.domain.Client;

public class JwtUtil {

  private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private static final long EXPIRATION_TIME = 120 * 1000;

  public static String gerarToken(Client client) {
    return Jwts.builder().setSubject(client.getName()).setIssuer(client.getCpf())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(key).compact();
  }

  public static String validarToken(String token) {
    try {
      Jws<Claims> parsed = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return parsed.getBody().getSubject();
    } catch (JwtException e) {
      return null;
    }
  }
}
