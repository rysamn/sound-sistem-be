package com.rez.soundsystem.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BarangKembaliDto {
    private Integer id;
    private Integer barangKeluarId;
    private Date tanggalKembali;
    private String kondisi;
    private String keterangan;

    
}
