package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.PelangganDao;
import com.rez.soundsystem.dto.PelangganDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PelangganService {
    @Autowired
    private PelangganDao dao;
    public List<PelangganDto> all(){ return dao.findAll(); }
    public PelangganDto byId(int id){ return dao.findById(id); }
    public int create(PelangganDto p){ return dao.insert(p); }
    public int update(PelangganDto p){ return dao.update(p); }
    public int delete(int id){ return dao.delete(id); }
}
