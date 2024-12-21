package com.uniprojects.wfspring.controller.szoba;

import com.uniprojects.wfspring.service.SzobaService;
import com.uniprojects.wfspring.service.dto.SzobaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/szoba")
public class AdminSzobaController {
    @Autowired
    SzobaService szobaService;

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
