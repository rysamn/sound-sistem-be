package com.rez.soundsystem.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class InvoiceDto {
    private Integer id;
    private Integer idKontrak;
    private Date tanggalInvoice;
    private Double totalTagihan;
    private String status;
    private Date tanggalPembayaran;
}
