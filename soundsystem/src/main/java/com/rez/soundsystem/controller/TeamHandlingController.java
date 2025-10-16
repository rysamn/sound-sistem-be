package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.TeamHandlingDto;
import com.rez.soundsystem.service.TeamHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@CrossOrigin("*")
public class TeamHandlingController {
    @Autowired
    TeamHandlingService service;

    @GetMapping
    public List<TeamHandlingDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public TeamHandlingDto get(@PathVariable int id) {
        return service.byId(id);
    }

    @PostMapping
    public String create(@RequestBody TeamHandlingDto t) {
        service.create(t);
        return "OK";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody TeamHandlingDto t) {
        t.setId(id);
        service.update(t);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "OK";
    }
}
