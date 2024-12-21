package com.uniprojects.wfspring.repository;

import com.uniprojects.wfspring.data.entity.JogosultsagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogosultsagRepository extends JpaRepository<JogosultsagEntity, Long> {

    JogosultsagEntity findByNev(String nev);
}
