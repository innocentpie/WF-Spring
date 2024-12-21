package com.uniprojects.wfspring.service.impl;

import com.uniprojects.wfspring.data.entity.SzobaEntity;
import com.uniprojects.wfspring.repository.SzobaRepository;
import com.uniprojects.wfspring.service.SzobaService;
import com.uniprojects.wfspring.service.dto.SzobaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SzobaServiceImpl implements SzobaService {
    @Autowired
    private SzobaRepository szobaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SzobaDto create(SzobaDto dto) {
        SzobaEntity entity = new SzobaEntity();
        modelMapper.map(dto, entity, "SzobaSkipId");
        szobaRepository.save(entity);

        SzobaDto rdto = modelMapper.map(entity, SzobaDto.class);
        return rdto;
    }

    @Override
    public SzobaDto get(Long id) {
        SzobaEntity entity = szobaRepository.getReferenceById(id);
        SzobaDto dto = modelMapper.map(entity, SzobaDto.class);
        return dto;
    }

    @Override
    public List<SzobaDto> getAll() {
        return szobaRepository.findAll()
                .stream()
                .map(x -> modelMapper.map(x, SzobaDto.class))
                .toList();
    }

    @Override
    public SzobaDto update(Long id, SzobaDto dto) {
        SzobaEntity entity = szobaRepository.getReferenceById(id);
        modelMapper.map(dto, entity, "SzobaSkipId");
        szobaRepository.save(entity);

        SzobaDto rdto = modelMapper.map(entity, SzobaDto.class);
        return rdto;
    }

    @Override
    public void delete(Long id) {
        szobaRepository.deleteById(id);
    }
}
