package com.rez.soundsystem.dto;

import lombok.Data;

@Data
public class PenggunaDto {
    private Integer idPengguna;
    private String username;
    private String namaLengkap;
    private String role;
}