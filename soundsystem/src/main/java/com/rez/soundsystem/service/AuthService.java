package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.PenggunaDao;
import com.rez.soundsystem.dto.LoginRequestDto;
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
}