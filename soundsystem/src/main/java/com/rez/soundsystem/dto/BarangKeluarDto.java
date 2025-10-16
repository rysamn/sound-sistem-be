package com.rez.soundsystem.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BarangKeluarDto {
    private int id;
    private int jobOrderId;
    private int inventoriId;
    private int jumlah;
    private Date tanggalKeluar;
    private String penanggungJawabGudang;
    private String soundEngineer;
    private String status;

}
