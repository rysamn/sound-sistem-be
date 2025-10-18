package com.rez.soundsystem.dto;

import lombok.Data;

@Data
public class JurnalDetailDto {
    private Integer id;
    private Integer idJurnal;
    private Integer idAkun;
    private Double debit;
    private Double kredit;
}