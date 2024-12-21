package com.uniprojects.wfspring.service;

import com.uniprojects.wfspring.service.dto.FoglalasDto;
import com.uniprojects.wfspring.service.exception.UnauthorizedException;

import java.util.List;

public interface FoglalasService {
    FoglalasDto create(FoglalasDto dto);
    FoglalasDto createAsUser(FoglalasDto dto, String email);

    List<FoglalasDto> getAllByUserEmail(String email);
    List<FoglalasDto> getAll();

    FoglalasDto get(Long id);
    FoglalasDto getAsUser(Long id, String email) throws UnauthorizedException;

    FoglalasDto update(Long id, FoglalasDto dto);
    FoglalasDto updateAsUser(Long id, FoglalasDto dto, String email) throws UnauthorizedException;

    void delete(Long id);
    void deleteAsUser(Long id, String email) throws UnauthorizedException;
}
