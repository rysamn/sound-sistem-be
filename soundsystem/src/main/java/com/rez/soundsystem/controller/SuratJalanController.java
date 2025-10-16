package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.SuratJalanDto;
import com.rez.soundsystem.service.SuratJalanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suratjalan")
@CrossOrigin("*")
public class SuratJalanController {

    @Autowired
    private SuratJalanService service;

    @GetMapping
    public List<SuratJalanDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SuratJalanDto findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody SuratJalanDto dto) {
        if (service.create(dto) > 0) {
            return new ResponseEntity<>("Surat Jalan berhasil dibuat.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Gagal membuat Surat Jalan.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody SuratJalanDto dto) {
        dto.setIdSuratJalan(id);
        if (service.update(dto) > 0) {
            return new ResponseEntity<>("Surat Jalan berhasil diperbarui.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Gagal memperbarui Surat Jalan.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>("Surat Jalan berhasil dihapus.", HttpStatus.OK);
    }
}