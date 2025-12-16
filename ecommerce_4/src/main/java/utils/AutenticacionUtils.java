
package utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public class AutenticacionUtils {

    private static final String CLAVE =
        "ClaveEcommerceEquipo04ClaveEcommerceEquipo04ClaveEcommerceEquipo04";

    private static final SecretKey SIGNING_KEY =
        Keys.hmacShaKeyFor(CLAVE.getBytes(StandardCharsets.UTF_8));

    public static final long TIEMPO_EXPIRACION = 3600000;

    public static String generarToken(Long idUsuario) {

        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + TIEMPO_EXPIRACION);

        return Jwts.builder()
                .setSubject(idUsuario.toString())
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(SIGNING_KEY)
                .compact();
    }

    public static Long extraerIdUsuario(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Long.parseLong(claims.getSubject());

        } catch (Exception e) {
            return null;
        }
    }
    
    public static Claims validarToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            return null;
        }
    }
}