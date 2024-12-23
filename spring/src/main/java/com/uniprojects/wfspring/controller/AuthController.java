package com.uniprojects.wfspring.controller;

import com.uniprojects.wfspring.service.AuthService;
import com.uniprojects.wfspring.service.dto.BejelentkezesDto;
import com.uniprojects.wfspring.service.dto.RegisztracioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/regisztracio")
    public void regisztracio(@RequestBody RegisztracioDto dto){
        authService.regisztracio(dto);
    }

    @PostMapping("/bejelentkezes")
    public String bejelentkezes(@RequestBody BejelentkezesDto dto){
        return authService.bejelentkezes(dto);
    }
}
