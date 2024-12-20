package com.uniprojects.wfspring.repository;

import com.uniprojects.wfspring.data.entity.FelhasznaloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FelhasznaloRepository extends JpaRepository<FelhasznaloEntity, Long> {

    FelhasznaloEntity findByEmail(String email);
}
