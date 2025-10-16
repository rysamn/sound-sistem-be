package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.NeracaDao;
import com.rez.soundsystem.dto.NeracaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeracaService {

    @Autowired
    private NeracaDao dao;

    public List<NeracaDto> findAll() {
        return dao.findAll();
    }

    public NeracaDto findById(int id) {
        return dao.findById(id);
    }

    public int create(NeracaDto dto) {
        return dao.insert(dto);
    }

    public int update(NeracaDto dto) {
        return dao.update(dto);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}