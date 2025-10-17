package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.JurnalDetailDao;
import com.rez.soundsystem.dto.JurnalDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurnalDetailService {

    @Autowired
    private JurnalDetailDao dao;

    public List<JurnalDetailDto> findAll() {
        return dao.findAll();
    }

    public JurnalDetailDto findById(int id) {
        return dao.findById(id);
    }

    public List<JurnalDetailDto> findByJurnalId(int idJurnal) {
        return dao.findByJurnalId(idJurnal);
    }

    public int create(JurnalDetailDto dto) {
        return dao.insert(dto);
    }

    public int update(JurnalDetailDto dto) {
        return dao.update(dto);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}