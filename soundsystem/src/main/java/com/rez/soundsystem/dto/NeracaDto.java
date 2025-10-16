package com.rez.soundsystem.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class NeracaDto {
    private Integer idNeraca;
    private Date periode;
    private Double totalAktiva;
    private Double totalKewajiban;
    private Double totalModal;
}