package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.PaketLayananDto;
import com.rez.soundsystem.service.PaketLayananService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paket-layanan")
@CrossOrigin("*")
public class PaketLayananController {

    @Autowired
    private PaketLayananService service;

    @GetMapping
    public ResponseEntity<List<PaketLayananDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaketLayananDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/nama/{nama}")
    public ResponseEntity<PaketLayananDto> findByNama(@PathVariable String nama) {
        return ResponseEntity.ok(service.findByNama(nama));
    }
}