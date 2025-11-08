package com.rez.soundsystem.dto;

import lombok.Data;

@Data
public class InventoriDto {
    private Integer id;
    private String noInventaris;
    private String namaBarang;
    private String ukuran;
    private String merek;
    private String fungsi_equipment;
    private String kelengkapan;
    private String foto; // Menerima Base64 dari frontend
}