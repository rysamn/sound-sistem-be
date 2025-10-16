package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.InventoriDto;
import com.rez.soundsystem.service.InventoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventori")
@CrossOrigin(origins = "*")
public class InventoriController {

    @Autowired
    private InventoriService service;

    @GetMapping
    public List<InventoriDto> all() { return service.getAll(); }

    @GetMapping("/{id}")
    public InventoriDto get(@PathVariable int id) { return service.getById(id); }

    @PostMapping
    public String create(@RequestBody InventoriDto dto) {
        service.create(dto);
        return "OK";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody InventoriDto dto) {
        dto.setId(id);
        service.update(dto);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "OK";
    }
}
