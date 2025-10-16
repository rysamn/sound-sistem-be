package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.PembayaranDto;
import com.rez.soundsystem.service.PembayaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pembayaran")
@CrossOrigin("*")
public class PembayaranController {
    @Autowired
    PembayaranService service;

    @GetMapping
    public List<PembayaranDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public PembayaranDto get(@PathVariable int id) {
        return service.byId(id);
    }

    @PostMapping
    public String create(@RequestBody PembayaranDto p) {
        service.create(p);
        return "OK";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody PembayaranDto p) {
        p.setId(id);
        service.update(p);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "OK";
    }
}
