package com.uniprojects.wfspring.service;

import com.uniprojects.wfspring.service.dto.SzobaDto;

import java.util.List;

public interface SzobaService {
    SzobaDto create(SzobaDto dto);

    SzobaDto get(Long id);

    List<SzobaDto> getAll();

    SzobaDto update(Long id, SzobaDto dto);

    void delete(Long id);
}
