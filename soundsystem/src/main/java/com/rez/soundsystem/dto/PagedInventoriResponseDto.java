package com.rez.soundsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class PagedInventoriResponseDto {
    private List<InventoriResponseDto> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}