package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.NeracaDto;
import com.rez.soundsystem.service.NeracaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/neraca")
@CrossOrigin("*")
public class NeracaController {

    @Autowired
    private NeracaService service;

    @GetMapping
    public List<NeracaDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public NeracaDto findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody NeracaDto dto) {
        if (service.create(dto) > 0) {
            return new ResponseEntity<>("Data Neraca berhasil dibuat.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Gagal membuat data Neraca.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody NeracaDto dto) {
        dto.setIdNeraca(id);
        if (service.update(dto) > 0) {
            return new ResponseEntity<>("Data Neraca berhasil diperbarui.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Gagal memperbarui data Neraca.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>("Data Neraca berhasil dihapus.", HttpStatus.OK);
    }
}