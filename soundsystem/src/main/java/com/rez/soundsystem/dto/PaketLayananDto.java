package com.rez.soundsystem.dto;

import lombok.Data;

@Data
public class PaketLayananDto {
    private Integer id;
    private String namaPaket; // "Paket A", "Paket B", "Custom"
    private String deskripsi;
    private Double hargaPerJam;
    private Integer minimumJam;
    private Boolean includesRecording;
    private Boolean includesLiveAudio;
    private Boolean includesVideoRecording;
    private String jenisStudio; // "live_room", "recording", "both"
}