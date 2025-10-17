package com.rez.soundsystem.dto;

import lombok.Data;

@Data
public class InventoriDto {
    private Integer id_barang;
    private String kode_inventori;
    private String nama_barang;
    private String ukuran;
    private String merek;
    private String fungsi_equipment;
    private String kelengkapan;
    private String status;
    private String foto;
    private String tipe;
    private String deskripsi;
}
