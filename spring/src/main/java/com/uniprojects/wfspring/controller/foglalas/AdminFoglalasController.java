package com.uniprojects.wfspring.controller.foglalas;

import com.uniprojects.wfspring.service.FoglalasService;
import com.uniprojects.wfspring.service.dto.FoglalasDto;
import com.uniprojects.wfspring.service.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/admin/api/foglalas")
public class AdminFoglalasController {
    @Autowired
    FoglalasService foglalasService;

    @GetMapping("/getAll")
    public List<FoglalasDto> getAll() {
        return foglalasService.getAll();
    }

    @GetMapping("/get")
    public FoglalasDto get(@RequestParam Long id) {
        return foglalasService.get(id);
    }

    @PostMapping("/create")
    public FoglalasDto create(@RequestBody FoglalasDto dto) {
        return foglalasService.create(dto);
    }

    @PutMapping("/update")
    public FoglalasDto update(@RequestParam Long id, @RequestBody FoglalasDto dto) {
        return foglalasService.update(id, dto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        foglalasService.delete(id);
    }
}
