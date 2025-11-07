package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.InventoriDao;
import com.rez.soundsystem.dto.InventoriDto;
import com.rez.soundsystem.dto.InventoriResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Base64;

@Service
public class InventoriService {

    @Autowired
    private InventoriDao dao;

    public Page<InventoriResponseDto> getAll(Pageable pageable, String search) {
        List<InventoriResponseDto> inventoriList = dao.findAll(pageable, search);
        long total = dao.count(search);
        return new PageImpl<>(inventoriList, pageable, total);
    }

    public InventoriResponseDto getById(int id) {
        // DAO sekarang langsung mengembalikan ResponseDTO
        InventoriResponseDto dto = dao.findById(id);
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
