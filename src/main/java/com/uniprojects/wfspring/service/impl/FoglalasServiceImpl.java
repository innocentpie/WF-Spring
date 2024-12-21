package com.uniprojects.wfspring.service.impl;

import com.uniprojects.wfspring.data.entity.FelhasznaloEntity;
import com.uniprojects.wfspring.data.entity.FoglalasEntity;
import com.uniprojects.wfspring.data.entity.SzobaEntity;
import com.uniprojects.wfspring.repository.FelhasznaloRepository;
import com.uniprojects.wfspring.repository.FoglalasRepository;
import com.uniprojects.wfspring.repository.SzobaRepository;
import com.uniprojects.wfspring.service.FoglalasService;
import com.uniprojects.wfspring.service.dto.FoglalasDto;
import com.uniprojects.wfspring.service.exception.UnauthorizedException;
import com.uniprojects.wfspring.service.mapper.FoglalasMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoglalasServiceImpl implements FoglalasService {
    @Autowired
    private FoglalasRepository foglalasRepository;

    @Autowired
    private SzobaRepository szobaRepository;

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FoglalasMapper foglalasMapper;

    @Override
    public FoglalasDto create(FoglalasDto dto) {
        SzobaEntity uSzoba = szobaRepository.getReferenceById(dto.getSzobaId());
        FelhasznaloEntity felhasznaloEntity = felhasznaloRepository.getReferenceById(dto.getFelhasznaloId());

        FoglalasEntity entity = new FoglalasEntity();
        foglalasMapper.foglalasDtoToEntitySkipId(dto, entity);
        entity.setSzoba(uSzoba);
        entity.setFelhasznalo(felhasznaloEntity);

        foglalasRepository.save(entity);

        return modelMapper.map(entity, FoglalasDto.class);
    }

    @Override
    public FoglalasDto createAsUser(FoglalasDto dto, String email) {
        SzobaEntity uSzoba = szobaRepository.getReferenceById(dto.getSzobaId());
        FelhasznaloEntity felhasznaloEntity = felhasznaloRepository.findByEmail(email);

        FoglalasEntity entity = new FoglalasEntity();
        foglalasMapper.foglalasDtoToEntitySkipId(dto, entity);
        entity.setSzoba(uSzoba);
        entity.setFelhasznalo(felhasznaloEntity);

        foglalasRepository.save(entity);

        return modelMapper.map(entity, FoglalasDto.class);
    }

    @Override
    public List<FoglalasDto> getAllByUserEmail(String email) {
        FelhasznaloEntity felhasznaloEntity = felhasznaloRepository.findByEmail(email);

        return foglalasRepository.findAllByFelhasznaloId(felhasznaloEntity.getId())
                .stream()
                .map(x -> modelMapper.map(x, FoglalasDto.class))
                .toList();
    }

    @Override
    public List<FoglalasDto> getAll() {
        return foglalasRepository.findAll()
                .stream()
                .map(x -> modelMapper.map(x, FoglalasDto.class))
                .toList();
    }

    @Override
    public FoglalasDto get(Long id) {
        FoglalasEntity entity = foglalasRepository.getReferenceById(id);
        return modelMapper.map(entity, FoglalasDto.class);
    }

    @Override
    public FoglalasDto getAsUser(Long id, String email) throws UnauthorizedException {
        FelhasznaloEntity felhasznaloEntity = felhasznaloRepository.findByEmail(email);
        FoglalasEntity foglalasEntity = foglalasRepository.getReferenceById(id);

        if(foglalasEntity.getFelhasznalo().getId() != felhasznaloEntity.getId())
            throw new UnauthorizedException();

        return modelMapper.map(foglalasEntity, FoglalasDto.class);
    }

    @Override
    public FoglalasDto update(Long id, FoglalasDto dto) {
        FoglalasEntity foglalasEntity = foglalasRepository.getReferenceById(id);

        FelhasznaloEntity uFelhasznalo = felhasznaloRepository.getReferenceById(dto.getFelhasznaloId());
        SzobaEntity uSzoba = szobaRepository.getReferenceById(dto.getSzobaId());

        foglalasMapper.foglalasDtoToEntitySkipId(dto, foglalasEntity);
        foglalasEntity.setFelhasznalo(uFelhasznalo);
        foglalasEntity.setSzoba(uSzoba);

        foglalasRepository.save(foglalasEntity);

        return modelMapper.map(foglalasEntity, FoglalasDto.class);
    }

    @Override
    public FoglalasDto updateAsUser(Long id, FoglalasDto dto, String email) throws UnauthorizedException {
        FelhasznaloEntity felhasznaloEntity = felhasznaloRepository.findByEmail(email);
        FoglalasEntity foglalasEntity = foglalasRepository.getReferenceById(id);

        if(foglalasEntity.getFelhasznalo().getId() != felhasznaloEntity.getId())
            throw new UnauthorizedException();

        SzobaEntity uSzoba = szobaRepository.getReferenceById(dto.getSzobaId());

        foglalasMapper.foglalasDtoToEntitySkipId(dto, foglalasEntity);
        foglalasEntity.setSzoba(uSzoba);

        foglalasRepository.save(foglalasEntity);
        foglalasRepository.save(foglalasEntity);

        return modelMapper.map(foglalasEntity, FoglalasDto.class);
    }

    @Override
    public void delete(Long id) {
        foglalasRepository.deleteById(id);
    }

    @Override
    public void deleteAsUser(Long id, String email) throws UnauthorizedException {
        FelhasznaloEntity felhasznaloEntity = felhasznaloRepository.findByEmail(email);
        FoglalasEntity foglalasEntity = foglalasRepository.getReferenceById(id);

        if(foglalasEntity.getFelhasznalo().getId() != felhasznaloEntity.getId())
            throw new UnauthorizedException();

        foglalasRepository.deleteById(id);
    }
}
