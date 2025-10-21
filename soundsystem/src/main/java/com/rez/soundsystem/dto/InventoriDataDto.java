package com.rez.soundsystem.dto;

import lombok.Data;

@Data
public class InventoriDataDto {
    private Integer id;
    private String noInventaris;
    private String namaBarang;
    // ... (semua field lain seperti di InventoriDto)
    private byte[] foto; // Tipe data untuk database
}