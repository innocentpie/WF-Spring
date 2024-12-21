package com.uniprojects.wfspring.controller;

import com.uniprojects.wfspring.service.SzobaService;
import com.uniprojects.wfspring.service.dto.SzobaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/szoba")
public class SzobaController {
    @Autowired
    SzobaService szobaService;

    @GetMapping("/getall")
    public List<SzobaDto> getAll() {
        return szobaService.getAll();
    }

    @GetMapping("/get")
    public SzobaDto get(@RequestParam Long id) {
        return szobaService.get(id);
    }

    @PostMapping("/create")
    public SzobaDto create(@RequestBody SzobaDto dto) {
        return szobaService.create(dto);
    }

    @PutMapping("/update")
    public SzobaDto update(@RequestParam Long id, @RequestBody SzobaDto dto) {
        return szobaService.update(id, dto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        szobaService.delete(id);
    }
}
