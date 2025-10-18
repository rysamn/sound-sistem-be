package com.rez.soundsystem.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BarangKembaliDto {
    private Integer id;
    private Integer idSuratJalan; 
    private Date tanggalKembali;
    private String kondisiBarang;
    private String penanggungJawaban;
    private String soundEngineer;
    private String keterangan;
}
