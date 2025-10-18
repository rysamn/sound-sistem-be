package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.JurnalDetailDto;
import com.rez.soundsystem.service.JurnalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jurnal-detail")
@CrossOrigin("*")
public class JurnalDetailController {

    @Autowired
    private JurnalDetailService service;

    @GetMapping
    public List<JurnalDetailDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public JurnalDetailDto findById(@PathVariable int id) {
        return service.findById(id);
    }

    // Endpoint untuk mencari semua detail berdasarkan ID Jurnal utamanya
    @GetMapping("/jurnal/{idJurnal}")
    public List<JurnalDetailDto> findByJurnalId(@PathVariable int idJurnal) {
        return service.findByJurnalId(idJurnal);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody JurnalDetailDto dto) {
        if (service.create(dto) > 0) {
            return new ResponseEntity<>("Data Detail Jurnal berhasil dibuat.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Gagal membuat data Detail Jurnal.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody JurnalDetailDto dto) {
        dto.setId(id);
        if (service.update(dto) > 0) {
            return new ResponseEntity<>("Data Detail Jurnal berhasil diperbarui.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Gagal memperbarui data Detail Jurnal.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if (service.delete(id) > 0) {
            return new ResponseEntity<>("Data Detail Jurnal berhasil dihapus.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Gagal menghapus data Detail Jurnal.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}