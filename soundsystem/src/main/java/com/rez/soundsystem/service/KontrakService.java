package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.KontrakDao;
import com.rez.soundsystem.dto.KontrakDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KontrakService {
    @Autowired
    private KontrakDao dao;

    public List<KontrakDto> findAll() {
        return dao.findAll();
    }

    public KontrakDto findById(int id) {
        return dao.findById(id);
    }

    public int create(KontrakDto p) {
        return dao.insert(p);
    }

    public int update(KontrakDto p) {
        return dao.update(p);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}
