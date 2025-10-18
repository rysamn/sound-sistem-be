package com.rez.soundsystem.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String username;
    private String password;
    private String namaLengkap;
    private String email;
    private String role;
}