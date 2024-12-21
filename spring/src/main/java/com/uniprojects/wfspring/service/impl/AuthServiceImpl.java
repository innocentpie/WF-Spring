package com.uniprojects.wfspring.service.impl;

import com.uniprojects.wfspring.data.entity.FelhasznaloEntity;
import com.uniprojects.wfspring.data.entity.JogosultsagEntity;
import com.uniprojects.wfspring.repository.FelhasznaloRepository;
import com.uniprojects.wfspring.repository.JogosultsagRepository;
import com.uniprojects.wfspring.service.AuthService;
import com.uniprojects.wfspring.service.JwtService;
import com.uniprojects.wfspring.service.dto.BejelentkezesDto;
import com.uniprojects.wfspring.service.dto.RegisztracioDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @Autowired
    private JogosultsagRepository jogRepo;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtService jwtService;

    @Override
    public void regisztracio(RegisztracioDto dto) {
        FelhasznaloEntity entity = modelMapper.map(dto, FelhasznaloEntity.class);
        entity.setJelszo(passwordEncoder.encode(entity.getJelszo()));

        JogosultsagEntity jog = jogRepo.findByNev("FELHASZNALO");
        if(jog == null){
            jog = new JogosultsagEntity();
            jog.setNev("FELHASZNALO");
            jog = jogRepo.save(jog);

            entity.setJogosultsagok(List.of(jog));
        } else {
            entity.setJogosultsagok(List.of(jog));
        }

        felhasznaloRepository.save(entity);
    }

    @Override
    public String bejelentkezes(BejelentkezesDto dto) {
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getJelszo()
                )
        );
        var user = felhasznaloRepository.findByEmail(dto.getEmail());
        return jwtService.generateToken(user);
    }
}
