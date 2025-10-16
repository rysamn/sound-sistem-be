package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.JurnalDao;
import com.rez.soundsystem.dto.JurnalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurnalService {

    @Autowired
    private JurnalDao dao;

    public List<JurnalDto> findAll() {
        return dao.findAll();
    }

    public JurnalDto findById(int id) {
        return dao.findById(id);
    }

    public int create(JurnalDto dto) {
        return dao.insert(dto);
    }

    public int update(JurnalDto dto) {
        return dao.update(dto);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}