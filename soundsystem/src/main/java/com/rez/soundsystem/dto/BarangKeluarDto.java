package com.rez.soundsystem.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BarangKeluarDto {
    private Integer id;
    private Integer idSuratJalan;
    private Integer kodeInventori;
    private Integer jumlah;
    private String penanggungJawabGudang;
    private String soundEngineer;
    private String status;

}
