package com.rez.soundsystem.util;

import com.rez.soundsystem.dto.PenggunaDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secretString;

    private SecretKey SECRET_KEY;

    @PostConstruct
    public void init() {
        // Mengonversi secret string dari properties menjadi objek SecretKey
        // Ini memastikan SECRET_KEY dibuat setelah secretString di-inject oleh Spring
        this.SECRET_KEY = Keys.hmacShaKeyFor(secretString.getBytes());
    }

    // Token berlaku selama 10 jam
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    /**
     * Menghasilkan token JWT berdasarkan detail pengguna.
     * 
     * @param penggunaDto Data pengguna yang berhasil login.
     * @return String token JWT.
     */
    public String generateToken(PenggunaDto penggunaDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", penggunaDto.getRole());
        claims.put("nama", penggunaDto.getNamaLengkap());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(penggunaDto.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // --- Metode untuk Validasi Token ---

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}