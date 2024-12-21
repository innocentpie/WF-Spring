package com.uniprojects.wfspring.service.mapper.impl;

import com.uniprojects.wfspring.data.entity.FoglalasEntity;
import com.uniprojects.wfspring.service.dto.FoglalasDto;
import com.uniprojects.wfspring.service.mapper.FoglalasMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FoglalasMapperImpl implements FoglalasMapper {
    @Override
    public void foglalasDtoToEntity(FoglalasDto source, FoglalasEntity target) {
        target.setId(source.getId());
        target.setElfoglalasDatum(source.getElfoglalasDatum());
        target.setElhagyasDatum(source.getElhagyasDatum());
    }

    @Override
    public void foglalasDtoToEntitySkipId(FoglalasDto source, FoglalasEntity target) {
        target.setId(source.getId());
        target.setElfoglalasDatum(source.getElfoglalasDatum());
        target.setElhagyasDatum(source.getElhagyasDatum());
    }
}
