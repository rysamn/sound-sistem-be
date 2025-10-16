package com.rez.soundsystem.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class JurnalDto {
    private Integer idJurnal;
    private Date tanggal;
    private String jenis;
    private String keterangan;
    private Double total;
}