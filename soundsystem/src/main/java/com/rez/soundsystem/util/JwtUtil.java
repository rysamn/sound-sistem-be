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
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function; // ✅ ini yang benar

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secretString;

    private SecretKey SECRET_KEY;

    @PostConstruct
    public void init() {
        // Konversi secret string dari application.properties jadi SecretKey
        this.SECRET_KEY = Keys.hmacShaKeyFor(secretString.getBytes());
    }

    // Token berlaku selama 10 jam
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    /**
     * Menghasilkan token JWT berdasarkan data pengguna
     */
    public String generateToken(PenggunaDto penggunaDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", penggunaDto.getRole());
        claims.put("nama", penggunaDto.getNamaLengkap());

        return Jwts.builder()
                .claims(claims)
                .subject(penggunaDto.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Ekstrak username dari token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Ekstrak tanggal kedaluwarsa dari token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Ambil satu klaim dari token
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Ambil seluruh klaim dari token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Cek apakah token sudah kedaluwarsa
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validasi token (username cocok & belum expired)
     */
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }
}
