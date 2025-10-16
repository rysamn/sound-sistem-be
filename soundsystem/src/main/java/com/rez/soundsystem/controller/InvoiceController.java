package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.InvoiceDto;
import com.rez.soundsystem.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
@CrossOrigin("*")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @GetMapping
    public List<InvoiceDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public InvoiceDto findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody InvoiceDto dto) {
        if (service.create(dto) > 0) {
            return new ResponseEntity<>("Invoice berhasil dibuat.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Gagal membuat invoice.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody InvoiceDto dto) {
        dto.setId(id);
        if (service.update(dto) > 0) {
            return new ResponseEntity<>("Invoice berhasil diperbarui.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Gagal memperbarui invoice.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>("Invoice berhasil dihapus.", HttpStatus.OK);
    }
}