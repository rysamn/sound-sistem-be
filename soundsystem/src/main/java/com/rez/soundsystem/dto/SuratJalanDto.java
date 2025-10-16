package com.rez.soundsystem.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class SuratJalanDto {
    private Integer idSuratJalan;
    private Integer idKontrak;
    private Date tanggalKeluar;
    private String ditandatanganiOleh;
    private String soundEngineer;
}