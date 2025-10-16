package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.PenggunaDao;
import com.rez.soundsystem.dto.PenggunaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PenggunaDao penggunaDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kita modifikasi PenggunaDao untuk bisa mencari berdasarkan username saja
        Optional<PenggunaDto> penggunaOptional = penggunaDao.findByUsername(username);
        PenggunaDto pengguna = penggunaOptional.orElseThrow(() -> new UsernameNotFoundException("User tidak ditemukan: " + username));

        // Buat objek UserDetails dari Spring Security
        return new User(pengguna.getUsername(), "", new ArrayList<>()); // Password dikosongkan karena validasi sudah di AuthController
    }
}