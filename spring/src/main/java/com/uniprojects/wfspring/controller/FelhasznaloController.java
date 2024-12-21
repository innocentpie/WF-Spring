package com.uniprojects.wfspring.controller;

import com.uniprojects.wfspring.service.UserService;
import com.uniprojects.wfspring.service.dto.FelhasznaloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/felhasznalo")
public class FelhasznaloController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    FelhasznaloDto getBejelentkezettFelhasznalo(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return userService.getByEmail(email);
    }
}
