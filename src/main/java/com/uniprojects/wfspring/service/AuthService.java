package com.uniprojects.wfspring.service;


import com.uniprojects.wfspring.service.dto.BejelentkezesDto;
import com.uniprojects.wfspring.service.dto.RegisztracioDto;

public interface AuthService {
    void regisztracio(RegisztracioDto dto);
    String  bejelentkezes(BejelentkezesDto dto);
}
