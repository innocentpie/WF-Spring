package com.uniprojects.wfspring.service;

import com.uniprojects.wfspring.service.dto.FelhasznaloDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService getUserDetailsService();

    FelhasznaloDto getByEmail(String email);

    List<FelhasznaloDto> getAll();
}
