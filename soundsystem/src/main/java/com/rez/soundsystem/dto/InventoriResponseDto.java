package com.rez.soundsystem.dto;

import lombok.Data;

@Data
public class InventoriResponseDto {
    private Integer id;
    private String noInventaris;
    private String namaBarang;
    private String ukuran;
    private String merek;
    private String fungsi_equipment;
    private String kelengkapan;
    private String foto; // Base64 encoded string
}