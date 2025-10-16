package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.KontrakDto;
import com.rez.soundsystem.service.KontrakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kontrak")
@CrossOrigin("*")
public class KontrakController {
    @Autowired
    KontrakService service;

    @GetMapping
    public List<KontrakDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public KontrakDto findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody KontrakDto p) {
        if (service.create(p) > 0) {
            return new ResponseEntity<>("Kontrak berhasil dibuat.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Gagal membuat kontrak.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody KontrakDto p) {
        p.setId(id);
        if (service.update(p) > 0) {
            return new ResponseEntity<>("Kontrak berhasil diperbarui.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Gagal memperbarui kontrak.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>("Kontrak berhasil dihapus.", HttpStatus.OK);
    }
}
