package com.rez.soundsystem.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class PenyewaanDto {
    private Integer id;
    private Integer pelangganId;
    private Integer jobOrderId;
    private Date tanggalSewa;
    private Date tanggalKembali;
    private Double totalBiaya;
    private String status;
    private String keterangan;
}
