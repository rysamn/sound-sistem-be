package com.rez.soundsystem.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class KontrakDto {
    private Integer id;
    private Integer idPelanggan;
    private String venue;
    private String acara;
    private LocalDateTime tanggalMulai;
    private LocalDateTime tanggalSelesai;
    private Double hargaSewa;
    private Double uangMuka;
    private Double pelunasan;
    private String metodeBayar;
    private String noRekening;
    private String status;
    private String keterangan;
}
