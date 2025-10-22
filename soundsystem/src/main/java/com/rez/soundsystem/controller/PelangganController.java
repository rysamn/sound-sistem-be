package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.PelangganDto;
import com.rez.soundsystem.service.PelangganService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/pelanggan")
@CrossOrigin("*")
public class PelangganController {

    private static final Logger logger = LoggerFactory.getLogger(InventoriController.class);

    @Autowired
    PelangganService service;

    @GetMapping
    public ResponseEntity<List<PelangganDto>> findAll() {
        try {
            return ResponseEntity.ok(service.all());
        } catch (Exception e) {
            logger.error("Error saat mengambil semua data Pelanggan: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

     @GetMapping("/{id}")
    public ResponseEntity<PelangganDto> findById(@PathVariable int id) {
        try {
            PelangganDto dto = service.byId(id);
            if (dto != null) {
                return ResponseEntity.ok(dto);
            } else {
                logger.warn("pelanggan dengan id {} tidak ditemukan.", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error saat mencari Pelanggan dengan id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

   @PostMapping
    public ResponseEntity<String> create(@RequestBody PelangganDto dto) {
        try {
            if (service.create(dto) > 0) {
                return new ResponseEntity<>("Data Pelanggan berhasil dibuat.", HttpStatus.CREATED);
            }
            logger.warn("Gagal membuat data Pelanggan, tidak ada baris yang terpengaruh. Data: {}", dto);
            return new ResponseEntity<>("Gagal membuat data Pelanggan.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Error saat membuat data Pelanggan: {}", e.getMessage(), e);
            return new ResponseEntity<>("Terjadi kesalahan pada server saat membuat data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody PelangganDto dto) {
        try {
            dto.setId(id);
            if (service.update(dto) > 0) {
                return new ResponseEntity<>("Data Pelanggan berhasil diperbarui.", HttpStatus.OK);
            }
            logger.warn("Gagal memperbarui data Pelanggan dengan id {}, tidak ada baris yang terpengaruh.", id);
            return new ResponseEntity<>("Gagal memperbarui data Pelanggan, mungkin data tidak ditemukan.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error saat memperbarui data Pelanggan dengan id {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Terjadi kesalahan pada server saat memperbarui data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            if (service.delete(id) > 0) {
                return new ResponseEntity<>("Data Pelanggan berhasil dihapus.", HttpStatus.OK);
            }
            logger.warn("Gagal menghapus data Pelanggan dengan id {}, tidak ada baris yang terpengaruh.", id);
            return new ResponseEntity<>("Gagal menghapus data Pelanggan, mungkin data tidak ditemukan.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error saat menghapus data Pelanggan dengan id {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Terjadi kesalahan pada server saat menghapus data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
