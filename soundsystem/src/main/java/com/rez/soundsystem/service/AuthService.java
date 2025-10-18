package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.PenggunaDao;
import com.rez.soundsystem.dto.LoginRequestDto;
import com.rez.soundsystem.dto.SignUpRequestDto;
import com.rez.soundsystem.dto.PenggunaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PenggunaDao penggunaDao;

    public Optional<PenggunaDto> login(LoginRequestDto loginRequest) {
        return penggunaDao.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    }

    public void register(SignUpRequestDto signUpRequest) throws Exception {
        // 1. Cek apakah username sudah ada
        if (penggunaDao.findByUsername(signUpRequest.getUsername()).isPresent()) {
            throw new Exception("Username '" + signUpRequest.getUsername() + "' sudah terdaftar. Silakan gunakan username lain.");
        }

        // 2. Cek apakah email sudah ada
        if (penggunaDao.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new Exception("Email '" + signUpRequest.getEmail() + "' sudah terdaftar. Silakan gunakan email lain.");
        }

        // 2. Simpan pengguna baru ke database
        // Untuk saat ini, password belum di-hash.
        penggunaDao.insert(signUpRequest);
    }
}