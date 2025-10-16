package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.InventoriDao;
import com.rez.soundsystem.dto.InventoriDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoriService {

    @Autowired
    private InventoriDao dao;

    public List<InventoriDto> getAll() {
        return dao.findAll();
    }

    public InventoriDto getById(int id) {
        return dao.findById(id);
    }

    public int create(InventoriDto dto) {
        return dao.insert(dto);
    }

    public int update(InventoriDto dto) {
        return dao.update(dto);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}
