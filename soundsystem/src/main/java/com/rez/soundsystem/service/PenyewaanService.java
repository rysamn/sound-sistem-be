package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.PenyewaanDao;
import com.rez.soundsystem.dto.PenyewaanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenyewaanService {
    @Autowired
    private PenyewaanDao dao;

    public List<PenyewaanDto> all() {
        return dao.findAll();
    }

    public PenyewaanDto byId(int id) {
        return dao.findById(id);
    }

    public int create(PenyewaanDto p) {
        return dao.insert(p);
    }

    public int update(PenyewaanDto p) {
        return dao.update(p);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}
