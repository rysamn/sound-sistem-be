package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.InventoriDao;
import com.rez.soundsystem.dto.InventoriDto;
import com.rez.soundsystem.dto.PagedInventoriResponseDto;
import com.rez.soundsystem.dto.InventoriResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Base64;

@Service
public class InventoriService {

    @Autowired
    private InventoriDao dao;

    public PagedInventoriResponseDto getAll(String search, int page, int size) {
        List<InventoriResponseDto> content = dao.findAll(search, page, size);
        long totalElements = dao.count(search);
        int totalPages = (int) Math.ceil((double) totalElements / size);

        PagedInventoriResponseDto response = new PagedInventoriResponseDto();
        response.setContent(content);
        response.setPage(page);
        response.setSize(size);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        return response;
    }

    public InventoriResponseDto getById(int id) {
        // DAO sekarang langsung mengembalikan ResponseDTO
        InventoriResponseDto dto = dao.findById(id);
        // Anda mungkin ingin menangani kasus jika dto adalah null (misalnya, melempar
        // exception)
        if (dto != null) {
            return dto;
        }
        return null; // Atau lempar ResourceNotFoundException
    }

    public int create(InventoriDto dto) {
        // Konversi Base64 String dari DTO ke byte[] untuk DAO
        byte[] fotoBytes = (dto.getFoto() != null && !dto.getFoto().isEmpty())
                ? Base64.getDecoder().decode(dto.getFoto())
                : null;
        return dao.insert(dto, fotoBytes);
    }

    public int update(InventoriDto dto) {
        byte[] fotoBytes = (dto.getFoto() != null && !dto.getFoto().isEmpty())
                ? Base64.getDecoder().decode(dto.getFoto())
                : null;
        return dao.update(dto, fotoBytes);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}
