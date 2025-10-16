package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.InvoiceDao;
import com.rez.soundsystem.dto.InvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceDao dao;

    public List<InvoiceDto> findAll() {
        return dao.findAll();
    }

    public InvoiceDto findById(int id) {
        return dao.findById(id);
    }

    public int create(InvoiceDto dto) {
        return dao.insert(dto);
    }

    public int update(InvoiceDto dto) {
        return dao.update(dto);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}