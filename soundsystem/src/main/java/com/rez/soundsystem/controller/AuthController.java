package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.LoginRequestDto;
import com.rez.soundsystem.dto.LoginResponseDto;
import com.rez.soundsystem.dto.PenggunaDto;
import com.rez.soundsystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rez.soundsystem.util.JwtUtil;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        // Tambahkan log untuk melihat data yang masuk
        System.out.println("Mencoba login dengan username: " + loginRequest.getUsername());

        Optional<PenggunaDto> penggunaOptional = authService.login(loginRequest);

        if (penggunaOptional.isPresent()) {
            PenggunaDto pengguna = penggunaOptional.get();
            String token = jwtUtil.generateToken(pengguna);
            return ResponseEntity.ok(new LoginResponseDto(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username atau password salah.");
        }
    }
}