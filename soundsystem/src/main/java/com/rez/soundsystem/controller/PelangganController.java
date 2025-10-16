package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.PelangganDto;
import com.rez.soundsystem.service.PelangganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pelanggan")
@CrossOrigin("*")
public class PelangganController {
    @Autowired
    PelangganService service;

    @GetMapping
    public List<PelangganDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public PelangganDto get(@PathVariable int id) {
        return service.byId(id);
    }

    @PostMapping
    public String create(@RequestBody PelangganDto p) {
        service.create(p);
        return "OK";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody PelangganDto p) {
        p.setIdPelanggan(id);
        service.update(p);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "OK";
    }
}
