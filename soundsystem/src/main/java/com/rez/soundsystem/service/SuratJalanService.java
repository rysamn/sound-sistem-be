package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.SuratJalanDao;
import com.rez.soundsystem.dto.SuratJalanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuratJalanService {

    @Autowired
    private SuratJalanDao dao;

    public List<SuratJalanDto> findAll() {
        return dao.findAll();
    }

    public SuratJalanDto findById(int id) {
        return dao.findById(id);
    }

    public int create(SuratJalanDto dto) {
        return dao.insert(dto);
    }

    public int update(SuratJalanDto dto) {
        return dao.update(dto);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}