package com.rez.soundsystem.util;

import com.rez.soundsystem.dto.PenggunaDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // ⚠️ PERINGATAN: Simpan secret key ini di tempat yang aman (misalnya application.properties atau environment variable), jangan di-hardcode.
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("IniAdalahSecretKeyYangSangatPanjangDanHarusSangatRahasiaSekali".getBytes());

    // Token berlaku selama 10 jam
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    /**
     * Menghasilkan token JWT berdasarkan detail pengguna.
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
}