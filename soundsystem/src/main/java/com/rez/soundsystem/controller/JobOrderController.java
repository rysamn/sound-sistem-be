package com.rez.soundsystem.controller;

import com.rez.soundsystem.dto.JobOrderDto;
import com.rez.soundsystem.service.JobOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/joborder")
@CrossOrigin("*")
public class JobOrderController {
    @Autowired
    JobOrderService service;

    @GetMapping
    public List<JobOrderDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public JobOrderDto get(@PathVariable int id) {
        return service.byId(id);
    }

    @PostMapping
    public String create(@RequestBody JobOrderDto j) {
        service.create(j);
        return "OK";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody JobOrderDto j) {
        j.setId(id);
        service.update(j);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "OK";
    }
}
