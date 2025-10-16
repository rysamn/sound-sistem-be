package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.BarangKembaliDao;
import com.rez.soundsystem.dto.BarangKembaliDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarangKembaliService {
    @Autowired private BarangKembaliDao dao;
    public List<BarangKembaliDto> all(){ return dao.findAll(); }
    public BarangKembaliDto byId(int id){ return dao.findById(id); }
    public int create(BarangKembaliDto b){ return dao.insert(b); }
    public int update(BarangKembaliDto b){ return dao.update(b); }
    public int delete(int id){ return dao.delete(id); }
}
