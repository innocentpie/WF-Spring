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
@RequestMapping("/api/foglalas")
public class FoglalasController {
    @Autowired
    FoglalasService foglalasService;

    @GetMapping("/getAll")
    public List<FoglalasDto> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return foglalasService.getAllByUserEmail(email);
    }

    @GetMapping("/get")
    public FoglalasDto get(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long id) {
        String email = userDetails.getUsername();

        try {
            return foglalasService.getAsUser(id, email);
        }
        catch (UnauthorizedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/create")
    public FoglalasDto create(@AuthenticationPrincipal UserDetails userDetails, @RequestBody FoglalasDto dto) {
        String email = userDetails.getUsername();
        return foglalasService.createAsUser(dto, email);
    }

    @PutMapping("/update")
    public FoglalasDto update(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long id, @RequestBody FoglalasDto dto) {
        String email = userDetails.getUsername();

        try {
            return foglalasService.updateAsUser(id, dto, email);
        }
        catch (UnauthorizedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/delete")
    public void delete(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long id) {
        String email = userDetails.getUsername();

        try {
            foglalasService.deleteAsUser(id, email);
        }
        catch (UnauthorizedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
