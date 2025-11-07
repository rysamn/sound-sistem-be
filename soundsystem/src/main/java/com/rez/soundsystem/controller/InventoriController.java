package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.InventoriDto;
import com.rez.soundsystem.dto.InventoriResponseDto;
import com.rez.soundsystem.service.InventoriService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventori")
@CrossOrigin(origins = "*")
public class InventoriController {

    private static final Logger logger = LoggerFactory.getLogger(InventoriController.class);

    @Autowired
    private InventoriService service;

    @GetMapping
    public ResponseEntity<Page<InventoriResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "") String search) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(service.getAll(pageable, search));
        } catch (Exception e) {
            logger.error("Error saat mengambil semua data inventori: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoriResponseDto> findById(@PathVariable int id) {
        try {
            InventoriResponseDto dto = service.getById(id);
            if (dto != null) {
                return ResponseEntity.ok(dto);
            } else {
                logger.warn("Inventori dengan id {} tidak ditemukan.", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error saat mencari inventori dengan id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody InventoriDto dto) {
        try {
            if (service.create(dto) > 0) {
                return new ResponseEntity<>("Data Inventori berhasil dibuat.", HttpStatus.CREATED);
            }
            logger.warn("Gagal membuat data inventori, tidak ada baris yang terpengaruh. Data: {}", dto);
            return new ResponseEntity<>("Gagal membuat data Inventori.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Error saat membuat data inventori: {}", e.getMessage(), e);
            return new ResponseEntity<>("Terjadi kesalahan pada server saat membuat data.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody InventoriDto dto) {
        try {
            dto.setId(id);
            if (service.update(dto) > 0) {
                return new ResponseEntity<>("Data Inventori berhasil diperbarui.", HttpStatus.OK);
            }
            logger.warn("Gagal memperbarui data inventori dengan id {}, tidak ada baris yang terpengaruh.", id);
            return new ResponseEntity<>("Gagal memperbarui data Inventori, mungkin data tidak ditemukan.",
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error saat memperbarui data inventori dengan id {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Terjadi kesalahan pada server saat memperbarui data.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            if (service.delete(id) > 0) {
                return new ResponseEntity<>("Data Inventori berhasil dihapus.", HttpStatus.OK);
            }
            logger.warn("Gagal menghapus data inventori dengan id {}, tidak ada baris yang terpengaruh.", id);
            return new ResponseEntity<>("Gagal menghapus data Inventori, mungkin data tidak ditemukan.",
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error saat menghapus data inventori dengan id {}: {}", id, e.getMessage(), e);
            return new ResponseEntity<>("Terjadi kesalahan pada server saat menghapus data.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
