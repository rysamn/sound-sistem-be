package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.PelangganDto;
import com.rez.soundsystem.service.PelangganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pelanggan")
@CrossOrigin("*")
public class PelangganController {
    @Autowired
    PelangganService service;

    @GetMapping
    public List<PelangganDto> findAll() {
        return service.all();
    }

    @GetMapping("/{id}")
    public PelangganDto findById(@PathVariable int id) {
        return service.byId(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody PelangganDto b) {
        if (service.create(b) > 0) {
            return new ResponseEntity<>("Data Barang Keluar berhasil dibuat.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Gagal membuat data Barang Keluar.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody PelangganDto b) {
        b.setId(id);
        if (service.update(b) > 0) {
            return new ResponseEntity<>("Data Barang Keluar berhasil diperbarui.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Gagal memperbarui data Barang Keluar.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>("Data Barang Keluar berhasil dihapus.", HttpStatus.OK);
    }
}
