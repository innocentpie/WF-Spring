package com.uniprojects.wfspring.service.impl;

import com.uniprojects.wfspring.repository.FelhasznaloRepository;
import com.uniprojects.wfspring.service.UserService;
import com.uniprojects.wfspring.service.dto.FelhasznaloDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return felhasznaloRepository.findByEmail(username);
            }
        };
    }

    @Override
    public FelhasznaloDto getByEmail(String email) {
        return modelMapper.map(felhasznaloRepository.findByEmail(email), FelhasznaloDto.class);
    }
}
