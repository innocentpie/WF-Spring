package com.uniprojects.wfspring.service.mapper;

import com.uniprojects.wfspring.data.entity.FoglalasEntity;
import com.uniprojects.wfspring.service.dto.FoglalasDto;

public interface FoglalasMapper {
    void foglalasDtoToEntity(FoglalasDto source, FoglalasEntity target);
    void foglalasDtoToEntitySkipId(FoglalasDto source, FoglalasEntity target);
}
