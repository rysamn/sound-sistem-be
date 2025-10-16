package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.PenyewaanDto;
import com.rez.soundsystem.service.PenyewaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penyewaan")
@CrossOrigin("*")
public class PenyewaanController {
    @Autowired
    PenyewaanService service;

    @GetMapping
    public List<PenyewaanDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public PenyewaanDto get(@PathVariable int id) {
        return service.byId(id);
    }

    @PostMapping
    public String create(@RequestBody PenyewaanDto p) {
        service.create(p);
        return "OK";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody PenyewaanDto p) {
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
