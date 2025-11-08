package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.PaketLayananDao;
import com.rez.soundsystem.dto.PaketLayananDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaketLayananService {
    
    @Autowired
    private PaketLayananDao dao;

    public List<PaketLayananDto> findAll() {
        return dao.findAll();
    }

    public PaketLayananDto findById(int id) {
        return dao.findById(id);
    }

    public PaketLayananDto findByNama(String nama) {
        return dao.findByNama(nama);
    }
}