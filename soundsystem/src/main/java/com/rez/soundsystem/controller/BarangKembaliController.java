package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.BarangKembaliDto;
import com.rez.soundsystem.service.BarangKembaliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barangkembali")
@CrossOrigin("*")
public class BarangKembaliController {
    @Autowired
    BarangKembaliService service;

    @GetMapping
    public List<BarangKembaliDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public BarangKembaliDto get(@PathVariable int id) {
        return service.byId(id);
    }

    @PostMapping
    public String create(@RequestBody BarangKembaliDto b) {
        service.create(b);
        return "OK";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody BarangKembaliDto b) {
        b.setId(id);
        service.update(b);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "OK";
    }
}
