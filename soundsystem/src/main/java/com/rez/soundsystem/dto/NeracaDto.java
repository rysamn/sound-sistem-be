package com.rez.soundsystem.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class NeracaDto {
    private Integer id;
    private String periode;
    private Double totalAktiva;
    private Double totalKewajiban;
    private Double totalModal;
}