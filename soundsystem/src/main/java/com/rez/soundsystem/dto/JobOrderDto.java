package com.rez.soundsystem.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class JobOrderDto {
    private Integer id;
    private String namaPenyewa;
    private String venue;
    private String acara;
    private LocalDateTime tanggalMulai;
    private LocalDateTime tanggalSelesai;
    private Double hargaSewa;
    private String statusPembayaran;
    private String metodeBayar;
    private String noRekening;
    private java.sql.Date tanggalOrder;
}
