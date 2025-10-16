package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.JurnalDto;
import com.rez.soundsystem.service.JurnalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jurnal")
@CrossOrigin("*")
public class JurnalController {

    @Autowired
    private JurnalService service;

    @GetMapping
    public List<JurnalDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public JurnalDto findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody JurnalDto dto) {
        if (service.create(dto) > 0) {
            return new ResponseEntity<>("Jurnal berhasil dibuat.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Gagal membuat Jurnal.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody JurnalDto dto) {
        dto.setIdJurnal(id);
        if (service.update(dto) > 0) {
            return new ResponseEntity<>("Jurnal berhasil diperbarui.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Gagal memperbarui Jurnal.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>("Jurnal berhasil dihapus.", HttpStatus.OK);
    }
}