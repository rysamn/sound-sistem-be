package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.InventoriDto;
import com.rez.soundsystem.service.InventoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventori")
@CrossOrigin(origins = "*")
public class InventoriController {

    @Autowired
    private InventoriService service;

    @GetMapping
    public List<InventoriDto> findAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public InventoriDto findById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody InventoriDto dto) {
        if (service.create(dto) > 0) {
            return new ResponseEntity<>("Data Inventori berhasil dibuat.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Gagal membuat data Inventori.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody InventoriDto dto) {
        dto.setId_barang(id);
        if (service.update(dto) > 0) {
            return new ResponseEntity<>("Data Inventori berhasil diperbarui.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Gagal memperbarui data Inventori.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if (service.delete(id) > 0) {
            return new ResponseEntity<>("Data Inventori berhasil dihapus.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Gagal menghapus data Inventori.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
