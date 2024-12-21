package com.uniprojects.wfspring;

import com.uniprojects.wfspring.data.entity.FoglalasEntity;
import com.uniprojects.wfspring.data.entity.SzobaEntity;
import com.uniprojects.wfspring.service.dto.FoglalasDto;
import com.uniprojects.wfspring.service.dto.SzobaDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WfspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(WfspringApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		ModelMapper m = new ModelMapper();

		m.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		m.getConfiguration().setSkipNullEnabled(true);

		m.createTypeMap(SzobaDto.class, SzobaEntity.class);
		m.createTypeMap(SzobaDto.class, SzobaEntity.class, "SzobaSkipId")
				.addMappings(mapper -> mapper.skip(SzobaEntity::setId));

		m.createTypeMap(FoglalasEntity.class, FoglalasDto.class)
				.addMapping(x -> x.getFelhasznalo().getId(), FoglalasDto::setFelhasznaloId)
				.addMapping(x -> x.getSzoba().getId(), FoglalasDto::setSzobaId);

		return m;
	}
}
