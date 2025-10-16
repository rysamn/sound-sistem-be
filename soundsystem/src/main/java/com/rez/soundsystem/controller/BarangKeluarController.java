package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.BarangKeluarDto;
import com.rez.soundsystem.service.BarangKeluarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barangkeluar")
@CrossOrigin("*")
public class BarangKeluarController {
    @Autowired
    BarangKeluarService service;

    @GetMapping
    public List<BarangKeluarDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public BarangKeluarDto get(@PathVariable int id) {
        return service.byId(id);
    }

    @PostMapping
    public String create(@RequestBody BarangKeluarDto b) {
        service.create(b);
        return "OK";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody BarangKeluarDto b) {
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
