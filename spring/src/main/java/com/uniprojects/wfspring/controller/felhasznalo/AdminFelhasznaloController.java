package com.uniprojects.wfspring.controller.felhasznalo;

import com.uniprojects.wfspring.service.UserService;
import com.uniprojects.wfspring.service.dto.FelhasznaloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/api/felhasznalo")
public class AdminFelhasznaloController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    List<FelhasznaloDto> getAll() {
        return userService.getAll();
    }
}
