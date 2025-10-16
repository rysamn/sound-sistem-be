package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.PembayaranDao;
import com.rez.soundsystem.dto.PembayaranDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PembayaranService {
    @Autowired
    private PembayaranDao dao;

    public List<PembayaranDto> all() {
        return dao.findAll();
    }

    public PembayaranDto byId(int id) {
        return dao.findById(id);
    }

    public int create(PembayaranDto p) {
        return dao.insert(p);
    }

    public int update(PembayaranDto p) {
        return dao.update(p);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}
