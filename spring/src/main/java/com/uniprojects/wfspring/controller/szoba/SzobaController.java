package com.uniprojects.wfspring.controller.szoba;

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

    @GetMapping("/getAll")
    public List<SzobaDto> getAll() {
        return szobaService.getAll();
    }

    @GetMapping("/get")
    public SzobaDto get(@RequestParam Long id) {
        return szobaService.get(id);
    }
}
