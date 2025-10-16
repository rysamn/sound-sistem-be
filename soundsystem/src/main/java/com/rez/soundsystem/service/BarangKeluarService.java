package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.BarangKeluarDao;
import com.rez.soundsystem.dto.BarangKeluarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarangKeluarService {
    @Autowired
    private BarangKeluarDao dao;

    public List<BarangKeluarDto> all() {
        return dao.findAll();
    }

    public BarangKeluarDto byId(int id) {
        return dao.findById(id);
    }

    public int create(BarangKeluarDto b) {
        return dao.insert(b);
    }

    public int update(BarangKeluarDto b) {
        return dao.update(b);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}
