package com.rez.soundsystem.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class PembayaranDto {
    private Integer id;
    private Integer jobOrderId;
    private Date tanggalBayar;
    private Double jumlah;
    private String metode;
    private String keterangan;
}
